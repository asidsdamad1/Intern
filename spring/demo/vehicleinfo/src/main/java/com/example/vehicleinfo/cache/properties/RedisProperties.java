package com.example.vehicleinfo.cache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cache.specs.redis")
public class RedisProperties {
    private Integer port;
    private String host;
    private Long defaultTTL;
}

