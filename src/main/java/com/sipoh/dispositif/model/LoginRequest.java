package com.sipoh.dispositif.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginRequest {
    
    private String email;
    private String password;

    
}
