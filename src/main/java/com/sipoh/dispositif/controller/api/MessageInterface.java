package com.sipoh.dispositif.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sipoh.dispositif.model.MessageRequest;

import io.swagger.v3.oas.annotations.Operation;

public interface MessageInterface {

    @PostMapping("/dispositif/{dispo_id}")
    @Operation(
        summary = "Envoyer un message aux proches de la victimes"
    )
    public void sendSms( @RequestBody MessageRequest messsage , @PathVariable String dispo_id);
    
} 
