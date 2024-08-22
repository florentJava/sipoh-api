package com.sipoh.dispositif.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import org.springframework.core.io.Resource;

import com.sipoh.dispositif.dtos.EnregistrementAudioDto;

import io.swagger.v3.oas.annotations.Operation;

public interface AudioInterface {


    @Operation(
    summary = "Sauvegarder un audio sur le serveur"
    )
    @PostMapping("dispositif/{dispo_id}")
    public EnregistrementAudioDto saveAudio(
        
        @RequestParam("audio") MultipartFile audioFile ,
        @RequestParam("lattitude") String lattitude , 
        @RequestParam("altitude") String  altitude,
        @RequestParam("longitude") String   longitude,
        @PathVariable("") String dispo_id
    );

    @Operation(
        summary = "Sauvegarder un audio sur le serveur"
    )
    @GetMapping("{audio_id}")
    public ResponseEntity<Resource>  getAudioFile(@PathVariable String audio_id);

    
}
