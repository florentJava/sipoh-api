package com.sipoh.dispositif.controller.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;

public interface GoogleInterface {
    
    @GetMapping("/login/google")
    @Operation(
        summary = "fournit une URL pour l'authentification Google"
    )
    public ResponseEntity<String> googleLogin(HttpServletResponse response) throws IOException;

    @Operation(
        summary = "Recuperation du token d'autorisation de google , demande du token d'information a Google ,  authentification du client"
    )
    @GetMapping("/callback/google")
    public RedirectView googleCallback(@RequestParam("code") String code) throws IOException;
}
