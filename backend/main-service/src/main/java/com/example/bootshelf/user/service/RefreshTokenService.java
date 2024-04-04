package com.example.bootshelf.user.service;

import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.entity.UserRefreshToken;
import com.example.bootshelf.user.repository.UserRefreshTokenRepository;
import com.example.bootshelf.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;
    @Value("${jwt.token.refresh-expiration-ms}")
    private Long expiredRefreshTokenTimeMs;
    private UserRepository userRepository;
    private UserRefreshToken userRefreshToken;
    private UserRefreshTokenRepository userRefreshTokenRepository;

    @Transactional
    public String recreateAccessToken(String oldAccessToken) throws JsonProcessingException {
        Integer userIdx = JwtUtils.getUserIdx(oldAccessToken, secretKey);

        if(userRefreshTokenRepository.findByUserIdx(userIdx).isPresent()){
            Optional<User> result = userRepository.findByIdx(userIdx);
            User subject = result.get();
            return JwtUtils.generateAccessToken(subject, secretKey, expiredTimeMs);
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token is expired.");
        }
    }

    @Transactional(readOnly = true)
    public void validateRefreshToken(String refreshToken, String oldAccessToken) throws JsonProcessingException {
        // 이거 try catch로 해야하나...?
        JwtUtils.extractAllClaims(refreshToken,secretKey);
        //
        Integer userIdx = JwtUtils.getUserIdx(oldAccessToken, secretKey);
        Optional <UserRefreshToken> result = userRefreshTokenRepository.findByUserIdx(userIdx);
        if(!result.isEmpty()){
            userRefreshToken.validateRefreshToken(refreshToken);
        } else {
            throw new ExpiredJwtException(null, null, "Refresh token expired.");
        }
    }
}
