package com.sipoh.dispositif.dtos;


import lombok.*;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EnregistrementAudioDto {

    @Size(min = 1, message = "L'ID doit contenir au moins un caractère")
    private String id;

    private String audioUrl;

    @NotNull(message = "La date ne doit pas etre null")
    private LocalDateTime dateTime;

    @NotNull(message = "La lattitude ne doit pas etre null")
    private String lattitude;

    @NotNull(message = "L'altitude ne doit pas etre null")
    private String altitude;

    @NotNull(message = "La longitude ne doit pas etre null")
    private String longitude;
 
    private DispositifDto dispositifDto;

}
