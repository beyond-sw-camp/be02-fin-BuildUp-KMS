package com.example.bootshelf.config.utils;


import com.example.bootshelf.certification.Certification;
import com.example.bootshelf.certification.repository.CertificationRepository;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.CourseException;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.example.bootshelf.course.Course;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.entity.UserRefreshToken;
import com.example.bootshelf.user.repository.UserRefreshTokenRepository;
import com.example.bootshelf.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final CertificationRepository certificationRepository;

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


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
        return extractAllClaims(token, key).get("idx", Integer.class);
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

//    private Integer decodeJwtPayloadSubject(String accessToken) throws JsonProcessingException {
//        String[] jwtParts = accessToken.split("\\."); // JWT를 '.'으로 분리하여 페이로드 부분 추출
//        String encodedPayload = jwtParts[1]; // 인코딩된 페이로드
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedPayload); // Base64 디코딩
//        String decodedPayload = new String(decodedBytes, StandardCharsets.UTF_8); // 디코딩된 페이로드
//        Map<String, Object> payloadMap = objectMapper.readValue(decodedPayload, new TypeReference<Map<String, Object>>(){}); // 페이로드를 맵 형태로 변환
//        return (Integer) payloadMap.get("idx"); // "idx" 필드 반환
//    }

//    public static String recreateAccessToken(String oldAccessToken, String secretKey, Long expiredTimeMs, Long reissueLimit) throws JsonProcessingException{
//
//        Integer userIdx = getUserIdx(oldAccessToken, secretKey);
//
//        Optional<User> result = userRepository.findByIdx(userIdx);
//        if(!result.isPresent()){
//            throw new UserException(ErrorCode.USER_NOT_EXISTS, String.format("User idx [ %s ] is not exists.", userIdx));
//        }
//        User user = result.get();
//        // 여기서부터는 같은 메소드.
//        if(userRefreshTokenRepository.findByUserIdxAndReissueCountLessThan(userIdx,reissueLimit).isPresent()){
//            UserRefreshToken refreshToken = userRefreshTokenRepository.findByUserIdxAndReissueCountLessThan(userIdx,reissueLimit).get();
//            refreshToken.increaseReissueCount(); // 재발급 횟수 증가
//            userRefreshTokenRepository.save(refreshToken); // 변경 내용 저장
//        } else {
//            throw new ExpiredJwtException(null, null, "Refresh token expired.");
//        }
//
//        return generateAccessToken(user,secretKey, expiredTimeMs);
//    }

    public static Jws<Claims> validateAndParseToken(String token, String secretKey) {	// validateTokenAndGetSubject에서 따로 분리
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token);
    }

    public static Integer validateRefreshToken(String refreshToken, String oldAccessToken, String secretKey) throws JsonProcessingException {
        validateAndParseToken(refreshToken, secretKey);
        Integer userIdx = getUserIdx(oldAccessToken,secretKey);

//        if(userRefreshTokenRepository.findByUserIdxAndReissueCountLessThan(userIdx,reissueLimit).isPresent()){
//            UserRefreshToken refreshToken = userRefreshTokenRepository.findByUserIdxAndReissueCountLessThan(userIdx,reissueLimit).get();
//            refreshToken.increaseReissueCount(); // 재발급 횟수 증가
//            userRefreshTokenRepository.save(refreshToken); // 변경 내용 저장
//        } else {
//            throw new ExpiredJwtException(null, null, "Refresh token expired.");
//        }
        return userIdx;
    }
}
