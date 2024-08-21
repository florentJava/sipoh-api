package com.sipoh.dispositif.controller.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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


    @Operation(
        summary = "recuperer la liste des dispositif",
        description = "",
        responses = {
        @ApiResponse(responseCode = "200", description = "operation effectuer avec succes"),
        }
    )
    @GetMapping("")
    public ResponseEntity<List<DispositifDto>> getAllDispositif();


    @Operation(
        summary = "recuperer les dispositifs d'un utilisateur",
        description = "",
        responses = {
        @ApiResponse(responseCode = "200", description = "operation effectuer avec succes"),
        }
    )
    @GetMapping("utilisateur/{user_id}")
    public ResponseEntity<List<DispositifDto>> getUserDispos(@PathVariable String user_id);

    @Operation(
        summary = "recuperer un dispositif a partir de son ID",
        description = "",
        responses = {
        @ApiResponse(responseCode = "200", description = "operation effectuer avec succes"),
        }
    )
    @GetMapping("/{dispo_id}")
    public ResponseEntity<DispositifDto> getDispoById(@PathVariable String dispo_id);


    @Operation(
        summary = "attibuer un dispositif a un utilisateur",
        description = "",
        responses = {
        @ApiResponse(responseCode = "200", description = "operation effectuer avec succes"),
        }
    )
    @PutMapping("/{dispo_id}/utilisateur/{user_id}")
    public ResponseEntity<DispositifDto> addUserToDispo(@PathVariable String dispo_id, @PathVariable String user_id);

    @Operation(
        summary = "Modifier le statut d'un dispositif",
        description = "",
        responses = {
        @ApiResponse(responseCode = "200", description = "operation effectuer avec succes"),
        }
    )
    @PutMapping("/{dispo_id}/statut/{statut}")
    public ResponseEntity<DispositifDto> setDispositifStatut(@PathVariable String dispo_id ,@PathVariable String statut);



    // @Operation(
    //     summary = "",
    //     description = "",
    //     responses = {
    //     @ApiResponse(responseCode = "200", description = "operation effectuer avec succes"),
    //     }
    // )
    // @PutMapping("/{dispo_id}/statut/{statut}")
    // public List getDispoContacts() ;
}
