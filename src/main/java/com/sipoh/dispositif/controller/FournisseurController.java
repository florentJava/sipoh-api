package com.sipoh.dispositif.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.FournisseurInterface;
import com.sipoh.dispositif.dtos.FournisseurDto;
import com.sipoh.dispositif.service.FournisseurService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/fournisseur")
@RequiredArgsConstructor
public class FournisseurController implements FournisseurInterface {

    private final FournisseurService fournisseurService;

    @Override
    public ResponseEntity<FournisseurDto> createModel(@Valid FournisseurDto fourni) {
        fourni = fournisseurService.create(fourni);

        return ResponseEntity.ok(fourni);
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> getAllFournisseur() {
        return ResponseEntity.ok(fournisseurService.getAll());
    }
    
}
