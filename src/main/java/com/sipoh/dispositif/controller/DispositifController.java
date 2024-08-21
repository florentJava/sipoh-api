package com.sipoh.dispositif.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipoh.dispositif.controller.api.DispositifInterface;
import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.service.DispositifService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispositif/")
public class DispositifController implements DispositifInterface {

    private final DispositifService dispositifService;


    @Override
    public ResponseEntity<DispositifDto> createDispositif(@Valid @RequestBody DispositifDto dispositifDto){
        System.out.println(dispositifDto);
        return ResponseEntity.ok().body(dispositifService.create(dispositifDto));
    }


    @Override
    public ResponseEntity<List<DispositifDto>> getAllDispositif() {
        List<DispositifDto> disposfDto = dispositifService.getAllDispo();
        return ResponseEntity.ok().body(disposfDto);
    }


    @Override
    public ResponseEntity<List<DispositifDto>> getUserDispos(String user_id) {
        // TODO Auto-generated method stub

        List<DispositifDto> dispoDtos = dispositifService.getUserDispositifs(user_id);

        return ResponseEntity.ok().body(dispoDtos);

    }


    @Override
    public ResponseEntity<DispositifDto> getDispoById(String dispo_id) {
        // TODO Auto-generated method stub

        DispositifDto dispoDto = dispositifService.getIspoById(dispo_id);
        
        return ResponseEntity.ok().body(dispoDto);
    }


    @Override
    public ResponseEntity<DispositifDto> addUserToDispo(String dispo_id, String user_id) {
        // TODO Auto-generated method stub
        DispositifDto dispositifDto = dispositifService.userToDispositif(user_id,dispo_id);
        return ResponseEntity.ok().body(dispositifDto);
    }


    @Override
    public ResponseEntity<DispositifDto> setDispositifStatut(String dispo_id,String statut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDispositifStatut'");
    }




    

}
