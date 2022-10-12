package com.example.vehicleinfo.configuration;


import com.example.vehicleinfo.VehicleInfoApplication;
import com.example.vehicleinfo.cache.RedisProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This integration Test requires following external dependencies to run:
 * 1.Redis
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VehicleInfoApplication.class)
class RedisCacheConfigurationITest {

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private RedisProperties redisProperties = null;

    @Test
    void redisCacheConfigurationPropertiesTest() {
        Integer cacheMap = redisProperties.getPort();
        Map<String, RedisCacheConfiguration> redisCacheConfiguration = redisCacheManager.getCacheConfigurations();
        assertEquals(java.util.Optional.of(redisCacheConfiguration.get(5l).getTtl().getSeconds()), cacheMap);
    }

}