package com.sipoh.dispositif.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sipoh.dispositif.dtos.ModelDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

public interface ModelInterface {


    @Operation(
        summary = "Inserer un nouveau model"
    )
    @PostMapping("")
    public ResponseEntity<ModelDto> createModel(@Valid @RequestBody ModelDto model);


    @Operation(
        summary = "Liste de tout les models"
    )
    @GetMapping("")
    public ResponseEntity<List<ModelDto>> getAllModel();

    
}
