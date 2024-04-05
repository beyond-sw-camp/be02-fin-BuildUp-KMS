package com.example.bootshelf.redis.service;

import com.example.bootshelf.config.utils.RedisUtils;
import com.example.bootshelf.user.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;


@RequiredArgsConstructor
@Service
public class RedisService {

    private final SendEmailService sendEmailService;
    private final RedisUtils redisUtil;

    @Value("${spring.mail.username}")
    private String configEmail;

    public String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < targetStringLength; i++) {
            int codePoint;
            do {
                codePoint = random.nextInt(122 - 48 + 1) + 48; // 48부터 122까지의 ASCII 코드
            } while (!((codePoint <= 57 || codePoint >= 65) && (codePoint <= 90 || codePoint >= 97))); // 숫자와 알파벳 문자인지 검사

            stringBuilder.appendCodePoint(codePoint);
        }

        String randomString = stringBuilder.toString();

        return randomString;
    }

}