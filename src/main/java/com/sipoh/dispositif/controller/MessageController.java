package com.sipoh.dispositif.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.MessageInterface;
import com.sipoh.dispositif.dtos.ContactUrgenceDto;
import com.sipoh.dispositif.model.MessageRequest;
import com.sipoh.dispositif.propertie.TwilioPropertie;
import com.sipoh.dispositif.service.ContactService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController implements MessageInterface {

    private final TwilioPropertie twilioPropertie;
    private final ContactService  contactService;


    @Override
    public void sendSms( @RequestBody MessageRequest messsage , @PathVariable String dispo_id) {


        List<ContactUrgenceDto> contacts = contactService.getUserContact(dispo_id);


        if (contacts != null && contacts.size() > 0) {

            Twilio.init(twilioPropertie.getSid(), twilioPropertie.getToken());


            for (ContactUrgenceDto contact : contacts) {
                
                Message message = Message.creator(new PhoneNumber(contact.getNumero()), new PhoneNumber(twilioPropertie.getNumber()), messsage.getMessage()).create();
                System.out.println("Message sent: " + message.getSid());

            }
            
        }
    


    }
    
}
