package com.sipoh.dispositif.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.DispositifInterface;
import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.service.DispositifService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispositif/")
public class DispositifController implements DispositifInterface {

    private final DispositifService dispositifService;


    @Override
    public ResponseEntity<DispositifDto> createDispositif(@Valid @RequestBody DispositifDto dispositifDto){
        System.out.println(dispositifDto);
        return ResponseEntity.ok().body(dispositifService.create(dispositifDto));
    }

}
