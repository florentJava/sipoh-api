package com.sipoh.dispositif.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sipoh.dispositif.model.LoginResponse;
import com.sipoh.dispositif.security.JwtIssuer;
import com.sipoh.dispositif.security.UserPrincipal;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public LoginResponse loginService(String username , String password) throws Exception{


            var uspt = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(uspt);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            UserPrincipal  principal =  (UserPrincipal) authentication.getPrincipal();

            var roles = principal.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority).toList();

            
            var token = jwtIssuer.issueToken(principal.getUserId(),principal.getEmail(), roles );
            
            return LoginResponse.builder()
            .accessToken(token)
            .build();

        
    }


    
    
}
