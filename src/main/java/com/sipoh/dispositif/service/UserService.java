package com.sipoh.dispositif.service;

import java.util.Optional;

import java.util.List;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.UserDto;
import com.sipoh.dispositif.entity.UserEntity;
import com.sipoh.dispositif.mapper.Usermapper;
import com.sipoh.dispositif.repository.UserEntityRepository;

import lombok.RequiredArgsConstructor;
import com.sipoh.dispositif.entity.enumeration.Role;
import com.sipoh.dispositif.exception.GeneralException;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final Usermapper usermapper;



    public Optional<UserEntity> getUserByEmail(String email) {

        Optional<UserEntity> opUser =  userEntityRepository.findByEmail(email);

        if(opUser.isPresent()){
            UserEntity user = opUser.get();
            return Optional.of(user);
        }

        return Optional.empty();
    }


    public UserDto create(UserEntity entity){


        Optional<UserEntity> opEntity = userEntityRepository.findByEmail(entity.getEmail());

        if (opEntity.isPresent()){
            throw new GeneralException("Cette email existe deja");
        }

        entity.setPassword(passwordEncoder().encode(entity.getPassword()));


        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);

        entity.setRoles(roles);

        userEntityRepository.save(entity);

        return usermapper.toDto(entity);




    }



    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
