package com.sipoh.dispositif.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.MessageDto;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(MessageDto messageDto) throws  MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(messageDto.getTo());
        message.setSubject(messageDto.getSubject());
        message.setText(messageDto.getBody());

        javaMailSender.send(message);
    }
    
}
