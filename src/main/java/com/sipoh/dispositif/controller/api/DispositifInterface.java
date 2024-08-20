package com.sipoh.dispositif.controller.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.sipoh.dispositif.dtos.DispositifDto;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.springframework.web.bind.annotation.RequestBody;

public interface DispositifInterface {
    @Operation(
        summary = "Inserer un nouveau dispositif",
        description = "Insere les parametre d'un dipositif dans la bd",
        responses = {
        @ApiResponse(responseCode = "200", description = "Dispositif insere avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
        }
    )
    @PostMapping("")
    public ResponseEntity<DispositifDto> createDispositif(@Valid @RequestBody DispositifDto dispositifDto);

}
