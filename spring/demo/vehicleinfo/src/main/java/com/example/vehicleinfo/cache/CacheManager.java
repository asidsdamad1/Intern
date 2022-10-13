package com.example.vehicleinfo.cache;

import com.example.vehicleinfo.cache.type.Cache;
import com.example.vehicleinfo.cache.type.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheManager {
    private final RedisUtils cacheUtils;

    @Bean
    public Cache getCache() {
        return cacheUtils;
    }

}
