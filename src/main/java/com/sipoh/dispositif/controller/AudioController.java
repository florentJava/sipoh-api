package com.sipoh.dispositif.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sipoh.dispositif.controller.api.AudioInterface;
import com.sipoh.dispositif.dtos.EnregistrementAudioDto;
import com.sipoh.dispositif.exception.GeneralException;
import com.sipoh.dispositif.service.AudioService;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enregistrement/")
public class AudioController implements AudioInterface {

    private final AudioService audioService;

    @Override
    public EnregistrementAudioDto saveAudio(MultipartFile audioFile, String lattitude, String altitude,
        String longitude,  String dispo_id) {
        
        LocalDateTime localDateTime = LocalDateTime.now();
        EnregistrementAudioDto audioDto = EnregistrementAudioDto.builder()
                                                .altitude(altitude)
                                                .lattitude(lattitude)
                                                .longitude(longitude)
                                                .dateTime(localDateTime)
                                                .build();
        EnregistrementAudioDto aud =  null;
        try {
            aud = audioService.create(audioDto, dispo_id, audioFile );
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneralException("Une erreur c'est produite : "+ e.getMessage());
        }
        
        return// The `@RequiredArgsConstructor` annotation in this Java code is a Lombok annotation
        // that generates a constructor with required arguments. In this case, it is used on the
        // `AudioController` class to generate a constructor that initializes the `audioService`
        // field, which is marked as `final` in the class. This annotation helps reduce
        // boilerplate code by automatically generating the constructor based on the final fields
        // in the class.
        aud ;
    }


    @Override
    public ResponseEntity<Resource> getAudioFile(String audio_id) {

        Resource resource = audioService.downloadAudio(audio_id);

        return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
                    
    }
    

}
