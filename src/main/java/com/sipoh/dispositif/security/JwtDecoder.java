package com.sipoh.dispositif.security;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sipoh.dispositif.propertie.JwtProperties;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties properties;


    public DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC256(properties.getSecret()))
                .build()
                .verify(token);
    }
    
}
