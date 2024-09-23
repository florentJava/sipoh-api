package com.sipoh.dispositif.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sipoh.dispositif.dtos.UserDto;
import com.sipoh.dispositif.entity.UserEntity;

import io.swagger.v3.oas.annotations.Operation;

public interface UserInterface {

    
    @PostMapping("")
    @Operation(
    summary = "Creer un utilisateur"
    )
    public UserDto createUser(@RequestBody UserEntity dto);


    @GetMapping("{email}")
    @Operation(
        summary = "Recuperer un utilisateur par son email"
    )
    public UserDto getUserByEmail(@PathVariable String email);


    @DeleteMapping("{email}")
    @Operation(
        summary = "Supprimer un utilisateur par son email"
    )
    public void deleteUserByEmail(@PathVariable String email);


}
