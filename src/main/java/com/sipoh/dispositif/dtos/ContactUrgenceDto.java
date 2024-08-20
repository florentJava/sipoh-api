package com.sipoh.dispositif.dtos;


import lombok.*;
import com.sipoh.dispositif.entity.enumeration.ContactProfil;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUrgenceDto {

    @Size(min = 1, message = "L'ID doit contenir au moins un caractère")
    private String id;

    @NotNull(message = "Le numero ne doit etre null")
    @Size(min = 6,max = 6 , message = "L'ID doit avoir 6 charactere")
    private Integer numero;

    @NotNull(message = "Le nom ne doit etre null")
    private String nom;

    @NotNull(message = "Le prenom ne doit etre null")
    private String prenom;

    @NotNull(message = "Le profil ne doit etre null")
    private ContactProfil profil; 

    private DispositifDto dispositifDto;
}
