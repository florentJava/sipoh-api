package com.sipoh.dispositif.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.sipoh.dispositif.entity.enumeration.DispositifStatut;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispositifDto {

    private String id;

    @NotNull(message = "La date de fabrication ne doit pas être null")
    private LocalDateTime dateFabrication;

    private DispositifStatut statut;

    @Size(min = 1, message = "L'utilisateur ID doit contenir au moins un caractère")
    private String utilisateurId;

    private FournisseurDto fournisseurDto;

    private ModelDto modelDto;

    private List<ContactUrgenceDto> contactsDto;

    private List<EnregistrementAudioDto> audiosDtos;

}
