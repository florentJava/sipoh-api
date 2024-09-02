package com.sipoh.dispositif.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.model.MessageRequest;
import com.sipoh.dispositif.propertie.TwilioPropertie;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final TwilioPropertie twilioPropertie;
    


    @PostMapping("/")
        public void sendSms( @RequestBody MessageRequest messsage) {
        
            System.out.println("Message recive : " + messsage.toString() );

            Twilio.init(twilioPropertie.getSid(), twilioPropertie.getToken());

        System.out.println("Message sent: " );
        Message message = Message.creator(new PhoneNumber(messsage.getTo()), new PhoneNumber(twilioPropertie.getNumber()), messsage.getMessage()).create();
        System.out.println("Message sent: " + message.getSid());

    }
    
}
