package com.sipoh.dispositif.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsSevices customUserDetailsSevices;


    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception{

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        
        http.
                cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .securityMatcher("/**")
                .authorizeRequests(authorize -> authorize
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/oauth2/**").permitAll()
                .requestMatchers("/oauth/**").permitAll()
                .requestMatchers("/v3/**").permitAll()                
                .requestMatchers("/message/**").permitAll()                
                .requestMatchers("/oauth2/**").permitAll()
                .anyRequest()
                // .permitAll()
                .authenticated()
                );
                
                

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticateActionManager(HttpSecurity http) throws Exception{


        
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);


        authenticationManagerBuilder
                .userDetailsService(customUserDetailsSevices)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

}
