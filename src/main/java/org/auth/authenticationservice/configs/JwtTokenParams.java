package org.auth.authenticationservice.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "token")
public record JwtTokenParams (
        long confirmAccountTokenTimeout,
        long accessTokenTimeout,
        long refreshTokenTimeout,
        String issuer
){}
