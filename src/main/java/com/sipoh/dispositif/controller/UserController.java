package com.sipoh.dispositif.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.dtos.UserDto;
import com.sipoh.dispositif.entity.UserEntity;
import com.sipoh.dispositif.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;



    @PostMapping("")
    public UserDto createUser(@RequestBody UserEntity dto) {
        //TODO: process POST request

        System.err.println(dto);
        return userService.create(dto);
    }
    
    
}
