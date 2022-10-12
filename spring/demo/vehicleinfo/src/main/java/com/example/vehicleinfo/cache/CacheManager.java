package com.example.vehicleinfo.cache;

import com.example.vehicleinfo.cache.type.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheManager {
    private final RedisUtils cacheUtils;

    @Value("${cache.enable}")
    private boolean enableCaching;

    @Bean
    public RedisUtils redisUtils() {
        if (enableCaching) {
            return cacheUtils;
        }
        return null;
    }

}
