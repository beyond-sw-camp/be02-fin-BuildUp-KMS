package com.example.bootshelf.redis.model;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisInfo {

    private String host;
    private Integer port;
    private String password;
    private RedisInfo master;
    private RedisInfo slave;
//    private List<RedisInfo> slaves;
}
