package org.auth.authenticationservice.jwt;

import org.auth.authenticationservice.configs.JwtTokenParams;
import org.auth.authenticationservice.entities.Role;
import org.auth.authenticationservice.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenProvider {

    private final JwtTokenParams jwtTokenParams;
    private final JwtEncoder jwtEncoder;

    public TokenProvider(JwtTokenParams jwtTokenParams, JwtEncoder jwtEncoder) {
        this.jwtTokenParams = jwtTokenParams;
        this.jwtEncoder = jwtEncoder;
    }

    public String accessToken(User user) {

        String scope= user.getRoles()
            .stream()
            .map(Role::getName).collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
            .subject(user.getUserId())
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(jwtTokenParams.accessTokenTimeout(), ChronoUnit.MINUTES))
            .issuer(jwtTokenParams.issuer())
            .claim("scope",scope)
            .claim("email",user.getEmail())
            .claim("firstname",user.getFirstname())
            .claim("lastname",user.getLastname())
            .claim("username",user.getUsername())
            .build();

        return jwtEncoder
            .encode(JwtEncoderParameters.from(jwtClaimsSet))
            .getTokenValue();
    }

    public String refreshToken(User user) {

        JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
            .subject(user.getUserId())
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(jwtTokenParams.refreshTokenTimeout(), ChronoUnit.MINUTES))
            .issuer(jwtTokenParams.issuer())
            .claim("username",user.getUsername())
            .claim("email",user.getEmail())
            .build();

        return jwtEncoder
            .encode(JwtEncoderParameters.from(jwtRefreshClaimsSet))
            .getTokenValue();
    }
}
