package com.example.bootshelf.config.utils;


import com.example.bootshelf.certification.Certification;
import com.example.bootshelf.certification.repository.CertificationRepository;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.CourseException;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.example.bootshelf.course.Course;
import com.example.bootshelf.user.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final CertificationRepository certificationRepository;


    // 로그인 사용자 토큰 생성
    public String generateAccessToken(User user, String secretKey, Long expiredTimeMs) {

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
        } catch (ExpiredJwtException e) {
            throw new UserException(ErrorCode.EXPIRED_VERIFICATION_TOKEN, String.format("Verification Token [ %s ] is expired.", token));
        }
    }
}
