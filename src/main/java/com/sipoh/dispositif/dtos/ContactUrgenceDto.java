package com.sipoh.dispositif.dtos;


import com.sipoh.dispositif.entity.enumeration.ContactProfil;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUrgenceDto {

    private String id;

    @NotNull(message = "Le numero ne doit etre null")
    private String numero;

    @NotNull(message = "Le nom ne doit etre null")
    private String nom;

    @NotNull(message = "Le prenom ne doit etre null")
    private String prenom;

    @NotNull(message = "Le profil ne doit etre null")
    private ContactProfil profil; 

    private DispositifDto dispositifDto;
}