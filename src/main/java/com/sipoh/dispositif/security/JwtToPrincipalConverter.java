package com.sipoh.dispositif.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtToPrincipalConverter {


    public UserPrincipal convertToUserPrincipal(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(jwt.getSubject())
                .email(jwt.getClaim("e").asString())
                .authorities(extractAuthorities(jwt))
                .build();
    }


    public List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {

        var claim = jwt.getClaim("a");

        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }

        return claim.asList(SimpleGrantedAuthority.class);
    }
}
