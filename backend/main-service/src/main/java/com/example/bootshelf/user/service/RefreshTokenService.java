package com.example.bootshelf.user.service;

import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.config.utils.RedisUtils;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
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

    private final JwtUtils jwtUtils;

    private final RedisUtils redisUtils;

    @Transactional
    public String recreateAccessToken(String oldAccessToken) throws JsonProcessingException {
        String userEmail = extractEmailFromAccessToken(oldAccessToken);

        if(redisUtils.existData(userEmail)){
            Optional<User> result = userRepository.findByEmail(userEmail);
            User subject = result.get();
            return JwtUtils.generateAccessToken(subject, secretKey, expiredTimeMs);
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token is expired.");
        }
    }

    @Transactional
    public String recreateRefreshToken(String oldRefreshToken, String newAccessToken) throws JsonProcessingException {
        String userEmail = JwtUtils.getUserEmail(newAccessToken,secretKey);

        if(redisUtils.existData(userEmail)){
            redisUtils.deleteData(redisUtils.getData(userEmail));
            String newRefreshToken = jwtUtils.generateRefreshToken(secretKey,expiredRefreshTokenTimeMs);
            redisUtils.setDataExpire(userEmail, newRefreshToken,expiredRefreshTokenTimeMs / 1000);
            return newRefreshToken;
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token is expired.");
        }
    }

    @Transactional(readOnly = true)
    public Boolean validateRefreshToken(String refreshToken, String oldAccessToken) throws JsonProcessingException {
        // 이거 try catch로 해야하나...?
        jwtUtils.extractAllClaims(refreshToken,secretKey);
        String userEmail = extractEmailFromAccessToken(oldAccessToken);
        String result= redisUtils.getData(userEmail);
        if(result!=null){

            if(result.equals(refreshToken)) {
                return true;
            } else {
                return false;
                // 다시 로그인 하게 해야 한다.
                // ex) 비정상적인 이용이 감지되었습니다. 다시 로그인 해주세요
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

    public static String extractEmailFromAccessToken(String accessToken) {
        String[] tokenParts = accessToken.split("\\.");
        if (tokenParts.length < 2) {
            // 올바르지 않은 JWT 형식
            return null;
        }

        String payloadBase64 = tokenParts[1];
        byte[] payloadBytes = Base64.getDecoder().decode(payloadBase64);
        String payloadJson = new String(payloadBytes);

        // JSON 문자열에서 "email" 값을 추출
        int emailStartIndex = payloadJson.indexOf("\"email\"");
        if (emailStartIndex == -1) {
            // "email" 필드가 없음
            return null;
        }

        int colonIndex = payloadJson.indexOf(":", emailStartIndex);
        int commaIndex = payloadJson.indexOf(",", colonIndex);
        String emailValue = payloadJson.substring(colonIndex + 1, commaIndex).trim();

        // 이메일 값을 반환
        return emailValue.replaceAll("\"", ""); // 큰따옴표 제거
    }
}
