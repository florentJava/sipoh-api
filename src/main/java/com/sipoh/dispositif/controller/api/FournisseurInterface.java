package com.sipoh.dispositif.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sipoh.dispositif.dtos.FournisseurDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

public interface FournisseurInterface {

    @Operation(
        summary = "Inserer un nouveau fournisseur"
    )
    @PostMapping("")
    public ResponseEntity<FournisseurDto> createModel(@Valid @RequestBody FournisseurDto model);


    @Operation(
        summary = "Liste de tout les Fournisseur"
    )
    @GetMapping("")
    public ResponseEntity<List<FournisseurDto>> getAllFournisseur();
    
}
