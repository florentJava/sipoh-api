package com.sipoh.dispositif.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.UserInterface;
import com.sipoh.dispositif.dtos.UserDto;
import com.sipoh.dispositif.entity.UserEntity;
import com.sipoh.dispositif.mapper.Usermapper;
import com.sipoh.dispositif.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController implements UserInterface {

    private final UserService userService;

    private final Usermapper usermapper;


    @Override
    public UserDto createUser(@RequestBody UserEntity dto) {
        System.err.println(dto);
        return userService.create(dto);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        Optional<UserEntity> opUser = userService.getUserByEmail(email);

        if(opUser.isPresent()){

            UserEntity user = opUser.get();
            return usermapper.toDto(user);

        }

        throw new EntityNotFoundException("User not found");
    }
    
    
    @Override
    public void deleteUserByEmail(String email) {
        userService.deleteByEmail(email);
    }

    
    
    
}
