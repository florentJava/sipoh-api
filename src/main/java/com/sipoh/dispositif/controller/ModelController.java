package com.sipoh.dispositif.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.ModelInterface;
import com.sipoh.dispositif.dtos.ModelDto;
import com.sipoh.dispositif.service.ModelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController implements ModelInterface {
    
    private final ModelService modelService;


    @Override
    public ResponseEntity<ModelDto> createModel(@Valid ModelDto model) {
        model  = modelService.create(model);

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<List<ModelDto>> getAllModel() {
        return ResponseEntity.ok(modelService.getAll());
    }


    
}
