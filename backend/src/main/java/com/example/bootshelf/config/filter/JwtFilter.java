package com.example.bootshelf.config.filter;


import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.ErrorResponse;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRepository;
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

    private JwtUtils jwtUtils;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.refresh-expiration-hours}")
    private Long refreshTime;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    public JwtFilter(String secretKey, UserRepository userRepository){
        this.userRepository = userRepository;
        this.secretKey = secretKey;
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
        } catch (UserException e) {
            // JwtUtils에서 던진 UserAccountException 처리
            handleJwtException(response, e);
        } catch (ServletException e) {
            // Spring Security 예외 처리
            handleJwtException(response, new UserException(ErrorCode.UNAUTHORIZED, e.getMessage()));
        } catch (ExpiredJwtException e){
            reissueAccessToken(request, response, filterChain, e);
        }

    }
    private String parseBearerToken(HttpServletRequest request, String headerName) {
        return Optional.ofNullable(request.getHeader(headerName))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }
    private void reissueAccessToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ,Exception exception) {
        try {
            String refreshToken = parseBearerToken(request, "Refresh-Token");
            if (refreshToken == null) {
                throw exception;
            }
            Long reissueLimit = reissueLimit(expiredTimeMs,refreshTime);
            String oldAccessToken = parseBearerToken(request, HttpHeaders.AUTHORIZATION);
            jwtUtils.validateRefreshToken(refreshToken, oldAccessToken, secretKey, reissueLimit);
            String newAccessToken = jwtUtils.recreateAccessToken(oldAccessToken,secretKey,expiredTimeMs, reissueLimit);
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
}