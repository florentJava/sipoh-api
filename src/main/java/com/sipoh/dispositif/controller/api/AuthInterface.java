package com.sipoh.dispositif.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sipoh.dispositif.model.LoginRequest;
import com.sipoh.dispositif.model.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;

public interface AuthInterface {  

    @PostMapping("/login")
    @Operation(
        summary = "Authentification de l'utilisateur avec email et mot de passe"
    )
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated  LoginRequest request);



    @PostMapping("/login/{email}")
    @Operation(
        summary = "Authentification de l'utilisateur avec email uniquement (sans mot de passe)"
    )
    public LoginResponse loginWithEmail(@PathVariable String email);
}
