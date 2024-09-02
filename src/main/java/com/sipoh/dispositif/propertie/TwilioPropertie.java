package com.sipoh.dispositif.propertie;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioPropertie {

    private String sid;
    private String token;
    private String number;

    // Getters and setters
}
