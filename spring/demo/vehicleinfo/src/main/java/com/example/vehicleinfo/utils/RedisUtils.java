package com.example.vehicleinfo.utils;

import com.example.vehicleinfo.common.Constants;
import com.example.vehicleinfo.dto.VehicleInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisUtils {
    @Value("${spring.redis.timeout}")
    private int REDIS_TIMEOUT;

    private final RedisTemplate<String, String> redisTemplate;

    public void setValue(final String key, VehicleInfoDto vehicleInfoDto) {
        String jsonInString = "";
        try {
            jsonInString = Constants.map().writeValueAsString(vehicleInfoDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(key, jsonInString);
        redisTemplate.expire(key, REDIS_TIMEOUT, TimeUnit.SECONDS);
    }

    public String getValue(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteKeyFormRedis(String key) {
        redisTemplate.delete(key);
    }
}
