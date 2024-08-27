package com.sipoh.dispositif.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

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

@RestController
@RequestMapping("/oauth2")
public class GoogleOAuthController {

    private static final String CLIENT_ID = "30509143239-3tscua2cc2pq59d6ot9ub71bhptotb66.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8082/cb/oauth2/callback/google";
    private static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    private static final String TOKEN_URI = "https://oauth2.googleapis.com/token";

    @GetMapping("/login/google")
    public void googleLogin(HttpServletResponse response) throws IOException {
        String authorizationUrl = AUTH_URI + "?response_type=code" +
                                "&client_id=" + CLIENT_ID +
                                "&redirect_uri=" + REDIRECT_URI +
                                "&scope=openid%20profile%20email";
        response.sendRedirect(authorizationUrl);


    }


@GetMapping("/callback/google")
public String googleCallback(@RequestParam("code") String code) throws IOException {

    System.out.println("Hello word");
    HttpTransport httpTransport = new NetHttpTransport();
    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
            httpTransport, 
            jsonFactory, 
            TOKEN_URI,
            CLIENT_ID,
            CLIENT_SECRET,
            code,
            REDIRECT_URI)
            .execute();

    String accessToken = tokenResponse.getAccessToken();

    // Utiliser le token d'accès pour obtenir des informations utilisateur
    Oauth2 oauth2 = new Oauth2.Builder(httpTransport, jsonFactory, new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken))
            .setApplicationName("sipoh-api")
            .build();

    Userinfo userInfo = oauth2.userinfo().get().execute();
    System.out.println("User Email: " + userInfo.getEmail());
    System.out.println("User Name: " + userInfo.getName());

    // Retourner les informations utilisateur ou les traiter selon vos besoins
    return "User Email: " + userInfo.getEmail() + ", User Name: " + userInfo.getName();
}
}