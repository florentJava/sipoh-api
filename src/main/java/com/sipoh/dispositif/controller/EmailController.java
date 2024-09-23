package com.sipoh.dispositif.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.EmailInterface;
import com.sipoh.dispositif.dtos.MessageDto;
import com.sipoh.dispositif.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email/")
public class EmailController implements EmailInterface {

    private final EmailService emailService;

    @Override
    public ResponseEntity login(MessageDto request) {

        System.out.println(request);

        try{

            emailService.sendSimpleEmail(request);
        }catch(MailException e){
            
            // e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Erreur lors de l'envoi de l'email");
        }

        return ResponseEntity.ok().body("Email envoyé avec succès");

    }
    
}
