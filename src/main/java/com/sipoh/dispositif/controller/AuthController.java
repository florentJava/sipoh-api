package com.sipoh.dispositif.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.AuthInterface;
import com.sipoh.dispositif.entity.UserEntity;
import com.sipoh.dispositif.model.LoginRequest;
import com.sipoh.dispositif.model.LoginResponse;
import com.sipoh.dispositif.repository.UserEntityRepository;
import com.sipoh.dispositif.security.JwtIssuer;
import com.sipoh.dispositif.service.AuthService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthInterface {

    private final AuthService authService;
    private final UserEntityRepository userRepo;
    private final JwtIssuer jwtIssuer;



    @PostMapping("/login")
    @Override
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated  LoginRequest request){

        try {
            return ResponseEntity.ok(authService.loginService(request.getEmail(), request.getPassword()));
        } catch (Exception e) {

            // Crée une ResponseEntity avec le statut HTTP 500 (Internal Server Error)
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(new LoginResponse("error"));        
        }
    }

    @PostMapping("/login/{email}")
    @Override
    public LoginResponse loginWithEmail(@PathVariable String email) {
        
        UserEntity userEntity = userRepo.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Email introuvable"));

                List<String> roles = new ArrayList<>();

        userEntity.getRoles().forEach(role -> {
            roles.add(role.toString());
        });

        String token = jwtIssuer.issueToken(userEntity.getEmail(), userEntity.getEmail(), roles);

        LoginResponse loginResponse = new LoginResponse(token);
        return loginResponse;
    }
    

    

}
