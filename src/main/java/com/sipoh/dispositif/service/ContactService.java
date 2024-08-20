package com.sipoh.dispositif.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.ContactUrgenceDto;
import com.sipoh.dispositif.entity.ContactUrgence;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.exception.GeneralException;
import com.sipoh.dispositif.mapper.ContactMapper;
import com.sipoh.dispositif.repository.ContactUrgenceRepo;
import com.sipoh.dispositif.repository.DispositifRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {
    
    private final ContactUrgenceRepo contactUrgenceRepo;
    private final DispositifRepo dispositifRepo;
    private final ContactMapper contactMapper;



    /*
     * Insertion d'un nouveau contact pour un dispositif
     */
    public ContactUrgenceDto create(ContactUrgenceDto contactDto , String userId){


        // L'id du dispositif existe t'il ? si oui on get le dispo 
        Dispositif dispo =  dispositifRepo.findById(contactDto.getDispositifDto()
                    .getId())
                    .orElseThrow( () -> new EntityNotFoundException("Dispositif d'ID "+contactDto.getDispositifDto().getId()+" introuvable"));

        // Verification du nombre d'ID d'un utilisateur ( maximum 3)
        if( dispo.getAudios().size() == 2 ){
            throw new GeneralException("Nombre de contact maximum (3) atteint ");
        }

        // Mise en forme du ContactUrgence
        ContactUrgence contactUrgence = contactMapper.toContactUrgence(contactDto);
        contactUrgence.setDispositif(dispo);
        contactUrgence = contactUrgenceRepo.save(contactUrgence);

        return contactMapper.toContactUrgenceDto(contactUrgence);
    }

    /*
     * supprimer un contact a partir de son ID
     */
    public void removeContact(String contactId){
        contactUrgenceRepo.deleteById(contactId);
    }


    /*
     * recuperer les contacts d'un utilisateur
     */
    public List<ContactUrgenceDto> getUserContact(String dispoId){

        Dispositif dispo = dispositifRepo.findById(dispoId).orElseThrow( () -> new EntityNotFoundException(""));

        List<ContactUrgence> contacts = contactUrgenceRepo.findByDispositif(dispo);
        List<ContactUrgenceDto> contactsDtos = contactMapper.mapContactUrgenceList(contacts);

        return contactsDtos;
    }

}