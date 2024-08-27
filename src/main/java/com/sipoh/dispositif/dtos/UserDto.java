package com.sipoh.dispositif.dtos;

import org.hibernate.validator.constraints.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sipoh.dispositif.entity.enumeration.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto{

    private String id;

    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    private String password;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    private List<Role> roles;
}