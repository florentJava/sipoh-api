package com.sipoh.dispositif.controller;

import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.model.LoginRequest;
import com.sipoh.dispositif.model.LoginResponse;
import com.sipoh.dispositif.service.AuthService;

import lombok.RequiredArgsConstructor;


import org.springframework.security.core.annotation.AuthenticationPrincipal;



@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated  LoginRequest request){


        System.out.println(request.toString());

        try {
            return ResponseEntity.ok(authService.loginService(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            // Crée une ResponseEntity avec le statut HTTP 500 (Internal Server Error)

            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(new LoginResponse("error"));        
        }
        

    }

    @PostMapping("/getGoogleCode")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}
