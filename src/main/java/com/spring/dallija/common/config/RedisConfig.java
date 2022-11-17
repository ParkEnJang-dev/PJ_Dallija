package com.spring.dallija.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * EnableRedisHttpSession은 application.yml 에서 storage-type: redis 설정 안해도 된다.
 */
@EnableRedisHttpSession
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public int port;

    //RedisTemplate는 서버에서 Redis 커맨드를 수행을 도와준다
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    //lettuce 방식
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory configuration = new LettuceConnectionFactory(host,port);
        return configuration;
    }
}
