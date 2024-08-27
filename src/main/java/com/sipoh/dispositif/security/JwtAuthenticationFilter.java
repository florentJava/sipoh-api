package com.sipoh.dispositif.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final  JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        

        try {
        // Votre logique de filtrage
            resolveToken(request)
            .map(jwtDecoder::decodeToken)
            .map(jwtToPrincipalConverter::convertToUserPrincipal)
            .map(UserPrincipalAthenticationToken::new)
            .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
            filterChain.doFilter(request, response);

        } catch (TokenExpiredException ex) {

            System.out.println("JwtAuthenticationFilter.doFilterInternal()");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Le token fournit a expire");
        }
    
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }
    
}
