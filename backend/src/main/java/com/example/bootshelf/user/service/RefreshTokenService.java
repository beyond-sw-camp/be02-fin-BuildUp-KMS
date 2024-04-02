package com.example.bootshelf.user.service;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.entity.UserRefreshToken;
import com.example.bootshelf.user.repository.UserRefreshTokenRepository;
import com.example.bootshelf.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;
    @Value("${jwt.token.refresh-expiration-ms}")
    private Long expiredRefreshTokenTimeMs;

    private final UserRepository userRepository;

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final JwtUtils jwtUtils;

    @Transactional
    public String recreateAccessToken(String oldAccessToken) throws JsonProcessingException {
        Integer userIdx = extractIdxFromAccessToken(oldAccessToken);

        if(userRefreshTokenRepository.findByUserIdx(userIdx).isPresent()){
            Optional<User> result = userRepository.findByIdx(userIdx);
            User subject = result.get();
            return JwtUtils.generateAccessToken(subject, secretKey, expiredTimeMs);
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token is expired.");
        }
    }

    @Transactional(readOnly = true)
    public Boolean validateRefreshToken(String refreshToken, String oldAccessToken) throws JsonProcessingException {
        // 이거 try catch로 해야하나...?
        jwtUtils.extractAllClaims(refreshToken,secretKey);
        //
        Integer userIdx = extractIdxFromAccessToken(oldAccessToken);
        Optional <UserRefreshToken> result = userRefreshTokenRepository.findByUserIdx(userIdx);
        if(result!=null){
            UserRefreshToken userRefreshToken = result.get();
            String ref = userRefreshToken.getRefreshToken();
            if(ref.equals(refreshToken)) {
                return true;
            } else {
                return false;
            }
//            userRefreshToken.validateRefreshToken(refreshToken);
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token expired.");
        }
    }

    public static int extractIdxFromAccessToken(String accessToken) {
        String[] tokenParts = accessToken.split("\\.");
        if (tokenParts.length < 2) {
            // 올바르지 않은 JWT 형식
            return -1;
        }

        String payloadBase64 = tokenParts[1];
        byte[] payloadBytes = Base64.getDecoder().decode(payloadBase64);
        String payloadJson = new String(payloadBytes);

        // JSON 문자열에서 "idx" 값을 추출
        int idxStartIndex = payloadJson.indexOf("\"idx\"");
        if (idxStartIndex == -1) {
            // "idx" 필드가 없음
            return -1;
        }

        int colonIndex = payloadJson.indexOf(":", idxStartIndex);
        int commaIndex = payloadJson.indexOf(",", colonIndex);
        String idxValue = payloadJson.substring(colonIndex + 1, commaIndex).trim();

        // "idx" 값을 정수로 변환하여 반환
        return Integer.parseInt(idxValue);
    }
}
