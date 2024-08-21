package com.sipoh.dispositif.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.ContactInterface;
import com.sipoh.dispositif.dtos.ContactUrgenceDto;
import com.sipoh.dispositif.dtos.Response;
import com.sipoh.dispositif.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact/")
public class ContactController  implements ContactInterface{
    
    private final ContactService contactService;

    @Override
    public ResponseEntity<ContactUrgenceDto> createContact(@Valid @RequestBody ContactUrgenceDto contactUrgenceDto, String dispo_id) {
        ContactUrgenceDto contactDto = contactService.create(contactUrgenceDto, dispo_id);
        return ResponseEntity.ok().body(contactDto);
    }

    @Override
    public ResponseEntity<Response> deleteContact(String contact_id) {

        contactService.removeContact(contact_id);
        
        return ResponseEntity.status(HttpStatus.OK).body(new Response("contact supprimer"));

    }

    @Override
    public ResponseEntity<Response> updateContact( @Valid @RequestBody ContactUrgenceDto contact, @PathVariable String contact_id ) {

        contactService.updateContact(contact_id, contact);

        return ResponseEntity.status(HttpStatus.OK).body(new Response("contact modifier avec succes"));

    }

    @Override
    public ResponseEntity<List<ContactUrgenceDto>>getAllContact() {
        List<ContactUrgenceDto> contactDtos = contactService.getAll();

        return ResponseEntity.ok().body(contactDtos);
    }

    @Override
    public ContactUrgenceDto getOneContact(String contact_id) {
        return contactService.getContactById(contact_id);
    }

}
