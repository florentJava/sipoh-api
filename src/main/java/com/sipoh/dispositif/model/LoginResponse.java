package com.sipoh.dispositif.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class LoginResponse {
    private final String accessToken;
}
