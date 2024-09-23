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

public class FournisseurDto {
    
    private String id;

    private String nom;

    private String email;

    private String tel;

    private List<String> dispositifIds;
}
