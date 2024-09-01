package com.sipoh.dispositif.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal principal;

    
    public UserPrincipalAthenticationToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}