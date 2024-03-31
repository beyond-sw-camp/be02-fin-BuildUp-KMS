package com.example.bootshelf.config.filter;


import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.ErrorResponse;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.entity.UserRefreshToken;
import com.example.bootshelf.user.repository.UserRefreshTokenRepository;
import com.example.bootshelf.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {

    private UserRepository userRepository;

    private UserRefreshTokenRepository userRefreshTokenRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.refresh-expiration-ms}")
    private Long refreshTime;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    public JwtFilter(String secretKey, UserRepository userRepository, Long refreshTime, Long expiredTimeMs){
        this.userRepository = userRepository;
        this.secretKey = secretKey;
        this.refreshTime = refreshTime;
        this.expiredTimeMs = expiredTimeMs;
    }

    public Long reissueLimit(Long expiredTimeMs, Long refreshTime){
        Long reissueLimit = this.refreshTime * 3600000 / this.expiredTimeMs;
        return reissueLimit;
    }


    // 필터에서 예외를 다루기 위한 처리
    private void handleJwtException(HttpServletResponse response, UserException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
        response.setContentType("application/json");

        // ErrorResponse 생성 및 반환
        ErrorCode errorCode;
        if (exception.getErrorCode() != null) {
            errorCode = exception.getErrorCode();
        } else {
            errorCode = ErrorCode.UNAUTHORIZED;
        }

        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), exception.getMessage());
        String jsonErrorResponse = new ObjectMapper().writeValueAsString(errorResponse);

        response.getWriter().write(jsonErrorResponse);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            String token;
            if (header != null && header.startsWith("Bearer ")) {
                token = header.split(" ")[1];
            } else {
                filterChain.doFilter(request, response);
                return;
            }

            String authority = JwtUtils.getAuthority(token, secretKey);

            if (authority.equals("ROLE_USER") || authority.equals("ROLE_ADMIN") || authority.equals("ROLE_AUTHUSER") || authority.equals("ROLE_KAKAO")){
                String userEmail = JwtUtils.getUserEmail(token, secretKey);
                if (userEmail != null) {
                    Optional<User> result = userRepository.findByEmail(userEmail);

                    if (result.isPresent()) {
                        User user = result.get();

                        // 인가하는 코드
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                user, null,
                                user.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    }
                }
            }
        } catch (ExpiredJwtException e){
            reissueAccessToken(request, response, filterChain, e, expiredTimeMs, refreshTime);
        } catch (UserException e) {
            // JwtUtils에서 던진 UserAccountException 처리
            handleJwtException(response, e);
        } catch (ServletException e) {
            // Spring Security 예외 처리
            handleJwtException(response, new UserException(ErrorCode.UNAUTHORIZED, e.getMessage()));
        }
    }
    private String parseBearerToken(HttpServletRequest request, String headerName) {
        return Optional.ofNullable(request.getHeader(headerName))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }
    private void reissueAccessToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ,Exception exception, Long expiredTimeMs, Long refreshTime) {
        try {
            String refreshToken = parseBearerToken(request, "Refresh-Token");
            if (refreshToken == null) {
                throw exception;
            }

            String oldAccessToken = parseBearerToken(request, HttpHeaders.AUTHORIZATION);
            Integer userIdx= JwtUtils.validateRefreshToken(refreshToken, oldAccessToken, secretKey);
            upReissueCount(userIdx, expiredTimeMs,refreshTime);

            String newAccessToken = recreateAccessToken(userIdx, secretKey,expiredTimeMs,refreshTime);
            String authority = JwtUtils.getAuthority(newAccessToken, secretKey);


            if (authority.equals("ROLE_USER") || authority.equals("ROLE_ADMIN") || authority.equals("ROLE_AUTHUSER") || authority.equals("ROLE_KAKAO")){
                String userEmail = JwtUtils.getUserEmail(newAccessToken, secretKey);
                if (userEmail != null) {
                    Optional<User> result = userRepository.findByEmail(userEmail);

                    if (result.isPresent()) {
                        User user = result.get();

                        // 인가하는 코드
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                user, null,
                                user.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }
    }

    public void upReissueCount (Integer userIdx, Long expiredTimeMs, Long refreshTime){
        Long reissueLimit = reissueLimit(expiredTimeMs, refreshTime);
        if(userRefreshTokenRepository.findByUserIdxAndReissueCountLessThan(userIdx,reissueLimit).isPresent()){
            UserRefreshToken refreshToken = userRefreshTokenRepository.findByUserIdxAndReissueCountLessThan(userIdx,reissueLimit).get();
            refreshToken.increaseReissueCount(); // 재발급 횟수 증가
            userRefreshTokenRepository.save(refreshToken); // 변경 내용 저장
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token expired.");
        }
    }

    public String recreateAccessToken(Integer userIdx, String secretKey, Long expiredTimeMs, Long refreshTime) throws JsonProcessingException {

        Optional<User> result = userRepository.findByIdx(userIdx);
        if(!result.isPresent()){
            throw new UserException(ErrorCode.USER_NOT_EXISTS, String.format("User idx [ %s ] is not exists.", userIdx));
        }
        User user = result.get();
        upReissueCount(userIdx,expiredTimeMs,refreshTime);

        return JwtUtils.generateAccessToken(user,secretKey, expiredTimeMs);
    }
}