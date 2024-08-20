package com.sipoh.dispositif.dtos;

import lombok.*;

import java.util.List;
import com.sipoh.dispositif.entity.enumeration.DispositifStatut;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispositifDto {

    @Size(min = 1, message = "L'ID doit contenir au moins un caractère")
    private String id;

    @NotNull(message = "La date de fabrication ne doit pas être null")
    private LocalDateTime dateFabrication;

    private DispositifStatut statut;

    @Size(min = 1, message = "L'utilisateur ID doit contenir au moins un caractère")
    private String utilisateurId;

    private List<ContactUrgenceDto> contactsDto;

    private List<EnregistrementAudioDto> audiosDtos;

}
