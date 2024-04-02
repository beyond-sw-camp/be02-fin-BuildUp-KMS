package com.example.bootshelf.config.utils;


import com.example.bootshelf.certification.repository.CertificationRepository;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRefreshTokenRepository;
import com.example.bootshelf.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.*;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final CertificationRepository certificationRepository;

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final UserRepository userRepository;


    // 로그인 사용자 토큰 생성
    public static String generateAccessToken(User user, String secretKey, Long expiredTimeMs) {

        Claims claims = Jwts.claims();
        claims.put("idx", user.getIdx());
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("nickName", user.getNickName());
        claims.put("ROLE", user.getAuthority());

        byte[] secretBytes = secretKey.getBytes();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public String generateRefreshToken(String secretKey, Long refreshExpiredTimeMs){

        byte[] secretBytes = secretKey.getBytes();

        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiredTimeMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS256)
                .compact();

        return refreshToken;
    }


    // 카카오 로그인 사용자 토큰 생성
    @Transactional
    public static String generateAccessTokenForOAuth(User user, String secretKey, Long expiredTimeMs) {

        Claims claims = Jwts.claims();
        claims.put("idx", user.getIdx());
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("nickName", user.getNickName());
        claims.put("ROLE", user.getAuthority());

        byte[] secretBytes = secretKey.getBytes();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }


    // 키 변환 메서드
    public static Key getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 사용자 이름 가져오는 메서드
    public static String getUserEmail(String token, String key) {
        return extractAllClaims(token, key).get("email", String.class);
    }

    public static String getAuthority(String token, String key) {
        return extractAllClaims(token, key).get("ROLE", String.class);
    }

    public static Integer getUserIdx(String token, String key){
        return extracAtllClaimsFromOld(token, key).get("idx", Integer.class);
    }
    public static Claims extracAtllClaimsFromOld(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰에서 정보를 가져오는 코드가 계속 중복되어 사용되기 때문에 별도의 메서드로 만들어서 사용하기 위한 것
    public static Claims extractAllClaims(String token, String key) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey(key))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new UserException(ErrorCode.INVALID_VERIFICATION_TOKEN, String.format("Verification Token [ %s ] is invalid.", token));
        }
    }
}
