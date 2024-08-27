package com.sipoh.dispositif.security;

import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class JwtProperties {
    private final String secretKey = "myscretkey"; 
}
