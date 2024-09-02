package com.sipoh.dispositif.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "google.oauth2")
@Data
public class GooglePropertie {


    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String authUri;
    private String tokenUri;
    
}
