package com.example.msaproject.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    public Key getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 사용자 이름 가져오는 메서드
    public String getUserEmail(String token) {
        return extractAllClaims(token).get("id").toString();
    }

    // 토근에서 정보를 가져오는 코드가 계속 중복되어 사용되기 때문에 별도의 메서드로 만들어서 사용하기 위한 것
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
