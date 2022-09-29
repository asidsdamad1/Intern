package com.todolist.security.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JWTConfig {
    @Autowired
    private JWTProperties jwtProperties;

    public JWTConfig(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    @Qualifier("secretKey")
    public SecretKey getSecretKey()  {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    @Bean
    @Qualifier("expireDate")
    public int getExpireDate()  {
        return jwtProperties.getExpirationDateInMs();
    }

    @Bean
    @Qualifier("refreshExpireDate")
    public int getRefreshExpireDate()  {
        return jwtProperties.getRefreshExpirationDateInMs();
    }

    @Bean
    @Qualifier("tokenPrefix")
    public String getTokenPrefix()  {
        return jwtProperties.getTokenPrefix();
    }

}
