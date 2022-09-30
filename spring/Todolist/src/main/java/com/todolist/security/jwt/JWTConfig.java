package com.todolist.security.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JWTConfig {

    private String secretKey;
    private String tokenPrefix;
    private int expirationDateInMs;
    private int refreshExpirationDateInMs;

    public JWTConfig() {
    }

    public String getStringSecretKey() {
        return secretKey;
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public int getExpirationDateInMs() {
        return expirationDateInMs;
    }

    public int getRefreshExpirationDateInMs() {
        return refreshExpirationDateInMs;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setExpirationDateInMs(int expirationDateInMs) {
        this.expirationDateInMs = expirationDateInMs;
    }

    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }
}
