package com.sipoh.dispositif.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {

    private String id;

    private String nom;

    private String description;

    private String prix;

    private List<String> dispositifIds;
    
}
