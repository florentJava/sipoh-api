package com.sipoh.dispositif.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sipoh.dispositif.dtos.MessageDto;

import io.swagger.v3.oas.annotations.Operation;

public interface EmailInterface {


@PostMapping("")
@Operation(
    summary = "Envoyer un email"
)


public ResponseEntity login(@RequestBody  MessageDto request);


}
