package com.sipoh.dispositif.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sipoh.dispositif.dtos.ContactUrgenceDto;
import com.sipoh.dispositif.dtos.Response;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

public interface ContactInterface {
    
    @Operation(
        summary = "Inserer un nouveau contact"
    )
    @PostMapping("dispositif/{dispo_id}")
    public ResponseEntity<ContactUrgenceDto> createContact(@Valid @RequestBody ContactUrgenceDto contactUrgenceDto , @PathVariable String dispo_id);


    @Operation(
        summary = "supprimer contact"
    )
    @DeleteMapping("{contact_id}")
    public ResponseEntity<Response> deleteContact(@PathVariable String contact_id);

    @Operation(
        summary = "modifier contact"
    )
    @PutMapping("{contact_id}")
    public ResponseEntity<Response> updateContact( @RequestBody ContactUrgenceDto contact,@PathVariable String contact_id);

    @Operation(
        summary = "obtenir la liste des contacts"
    )
    @GetMapping("")
    public ResponseEntity<List<ContactUrgenceDto>> getAllContact();

    @Operation(
        summary = "obtenir un contact"
    )
    @GetMapping("{contact_id}")
    public ContactUrgenceDto getOneContact(@PathVariable String contact_id);


}
