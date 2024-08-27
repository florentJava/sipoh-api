package com.sipoh.dispositif.security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties properties;


    public String issueToken(String userId, String email ,List<String> roles) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt( Instant.now().plus ( Duration.of(10,ChronoUnit.MINUTES) )  )
                .withClaim("e", email)  
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
    
}