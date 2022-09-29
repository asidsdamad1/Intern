package com.todolist.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.jwt")
public class JWTProperties {

    private String secretKey;
    private String tokenPrefix;
    private int expirationDateInMs;
    private int refreshExpirationDateInMs;

    public JWTProperties() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public int getExpirationDateInMs() {
        return expirationDateInMs;
    }

    public void setExpirationDateInMs(int expirationDateInMs) {
        this.expirationDateInMs = expirationDateInMs;
    }

    public int getRefreshExpirationDateInMs() {
        return refreshExpirationDateInMs;
    }

    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }
}
