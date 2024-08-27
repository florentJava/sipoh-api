package com.sipoh.dispositif.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sipoh.dispositif.entity.UserEntity;
import com.sipoh.dispositif.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsSevices  implements UserDetailsService{

    private final UserService userService;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userService.getUserByEmail(username).orElseThrow();

        List<SimpleGrantedAuthority> autorities = new ArrayList<>() ;

        user.getRoles().forEach(rl -> {
            autorities.add(new SimpleGrantedAuthority(rl.toString()));
        });

        return UserPrincipal.builder()
                .email(user.getEmail())
                .userId(user.getId())
                .password(user.getPassword())
                .authorities( autorities )
                .build();   
    }
    
}
