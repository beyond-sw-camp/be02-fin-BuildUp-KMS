package com.example.bootshelf.user.service;


import com.example.bootshelf.config.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EmailVerifyService {

    private final RedisUtils redisUtils;

    @Transactional(readOnly = false)
    public void create(String email, String token) {
        redisUtils.setDataExpire(email,token,86400);
    }

    public Boolean verify(String email) {
        String uuid = redisUtils.getData(email);
        if(uuid!=null) {
            return true;
        }
        return false;
    }
}