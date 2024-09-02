package com.sipoh.dispositif.propertie;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "audio")
@Data
public class AudioPropertie {

    private String path;    
}
