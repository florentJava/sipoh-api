package com.sipoh.dispositif.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import com.sipoh.dispositif.controller.api.GoogleInterface;
import com.sipoh.dispositif.entity.UserEntity;
import com.sipoh.dispositif.model.LoginResponse;
import com.sipoh.dispositif.propertie.GooglePropertie;
import com.sipoh.dispositif.repository.UserEntityRepository;
import com.sipoh.dispositif.security.JwtIssuer;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class GoogleOAuthController implements GoogleInterface {

    private final UserEntityRepository userRepo;
    private final JwtIssuer jwtIssuer;
    private final GooglePropertie googlePropertie;

    @GetMapping("/login/google")
    @Override
    public ResponseEntity<String> googleLogin(HttpServletResponse response) throws IOException {
        String authorizationUrl = googlePropertie.getAuthUri() + "?response_type=code" +
                "&client_id=" + googlePropertie.getClientId() +
                "&redirect_uri=" + googlePropertie.getRedirectUri() +
                "&scope=openid%20profile%20email";

        return ResponseEntity.status(HttpStatus.FOUND).body(authorizationUrl);
    }

    @GetMapping("/callback/google")
    @Override
    public RedirectView googleCallback(@RequestParam("code") String code) throws IOException {

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                httpTransport,
                jsonFactory,
                googlePropertie.getTokenUri(),
                googlePropertie.getClientId(),
                googlePropertie.getClientSecret(),
                code,
                googlePropertie.getRedirectUri())
                .execute();

        String accessToken = tokenResponse.getAccessToken();

        // Utiliser le token d'accès pour obtenir des informations utilisateur
        Oauth2 oauth2 = new Oauth2.Builder(httpTransport, jsonFactory,
                new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken))
                .setApplicationName("sipoh-api")
                .build();

        Userinfo userInfo = oauth2.userinfo().get().execute();

        UserEntity userEntity = userRepo.findByEmail(userInfo.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email introuvable"));


        List<String> roles = new ArrayList<>();

        userEntity.getRoles().forEach(role -> {
            roles.add(role.toString());
        });

        String token = jwtIssuer.issueToken(userEntity.getEmail(), userEntity.getEmail(), roles);
        LoginResponse response = new LoginResponse(token);
        String redirectUrl = "http://localhost:4200/login?token=" + token;

        return new RedirectView(redirectUrl);
    }
}