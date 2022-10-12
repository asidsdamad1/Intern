package com.example.vehicleinfo.cache.type;

import com.example.vehicleinfo.cache.CacheManager;
import com.example.vehicleinfo.common.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisUtils extends CacheManager {
    @Value("${cache.specs.redis.default-ttl}")
    private int redisTTL;
    @Value("${cache.enable}")
    private String enableCaching;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void setValue(final String key, Object object) {
        if("true".equals(enableCaching)) {
            String jsonInString = "";
            try {
                jsonInString = Constants.map().writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            redisTemplate.opsForValue().set(key, jsonInString);
            redisTemplate.expire(key, redisTTL, TimeUnit.SECONDS);
        }
    }

    @Override
    public String getValue(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
