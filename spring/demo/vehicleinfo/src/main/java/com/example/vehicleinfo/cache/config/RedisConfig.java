package com.example.vehicleinfo.cache.config;

import com.example.vehicleinfo.cache.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class RedisConfig {
    private final RedisProperties redisProperties;

    private RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(timeoutInSeconds));
    }


    @Bean
    public org.springframework.cache.CacheManager redisCacheManager() {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        return RedisCacheManager
                .builder(redisConnectionFactory())
                .cacheDefaults(createCacheConfiguration(redisProperties.getDefaultTTL()))
                .withInitialCacheConfigurations(cacheConfigurations).build();

    }

    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}
