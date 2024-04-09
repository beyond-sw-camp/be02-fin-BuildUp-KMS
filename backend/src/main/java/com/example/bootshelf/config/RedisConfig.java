package com.example.bootshelf.config;

import com.example.bootshelf.redis.model.RedisInfo;
import io.lettuce.core.ReadFrom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {

    private final RedisInfo redisInfo;
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)    // replica에서 우선적으로 읽지만 replica에서 읽어오지 못할 경우 Master에서 읽어옴
                .build();
        // replica 설정
        RedisStaticMasterReplicaConfiguration slaveConfig = new RedisStaticMasterReplicaConfiguration(
                redisInfo.getMaster().getHost(), redisInfo.getMaster().getPort());

        String password = redisInfo.getMaster().getPassword(); // 또는 slave에서 가져오셔야 할지 확인
        if (password != null && !password.isEmpty()) {
            slaveConfig.setPassword(password);
        }
        // 설정에 slave 설정 값 추가
//        redisInfo.getSlave().forEach(slave -> slaveConfig.addNode(slave.getHost(), slave.getPort()));
        RedisInfo slave = redisInfo.getSlave();
        slaveConfig.addNode(slave.getHost(), slave.getPort());

        return new LettuceConnectionFactory(slaveConfig, clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }
}
