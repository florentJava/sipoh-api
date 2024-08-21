package com.sipoh.dispositif.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.ContactUrgenceDto;
import com.sipoh.dispositif.entity.ContactUrgence;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.exception.GeneralException;
import com.sipoh.dispositif.mapper.ContactMapper;
import com.sipoh.dispositif.repository.ContactUrgenceRepo;
import com.sipoh.dispositif.repository.DispositifRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ContactUrgenceDto create(ContactUrgenceDto contactDto , String dispo_id){


        // L'id du dispositif existe t'il ? si oui on get le dispo 
        Dispositif dispo =  dispositifRepo.findById(dispo_id)
                    .orElseThrow( () -> new EntityNotFoundException("Dispositif d'ID "+dispo_id+" introuvable"));

        // Verification du nombre d'ID d'un dispositif ( maximum 3)
        if( dispo.getContacts().size() >= 2 ){
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

    /*
     * modifier un contact
     */

    public ContactUrgenceDto updateContact(String contact_id , ContactUrgenceDto ctDto){

        ContactUrgence ct = contactUrgenceRepo
                                    .findById(contact_id).orElseThrow(()-> new EntityNotFoundException(" Contact non trouve : ID = "+contact_id));
        
        ct.setNom(ctDto.getNom());
        ct.setNumero(ctDto.getNumero());
        ct.setPrenom(ctDto.getPrenom());
        ct.setProfil(ctDto.getProfil());

        ct = contactUrgenceRepo.save(ct);

        return contactMapper.toContactUrgenceDto(ct);
    }


    /*
     * Reuperer la liste de tous les contacts
     */

    public List<ContactUrgenceDto> getAll(){
        List<ContactUrgence> contacts = contactUrgenceRepo.findAll();
        List<ContactUrgenceDto> contactsDto = contactMapper.mapContactUrgenceList(contacts);

        contactsDto.forEach(ctDto ->{
            ctDto.getDispositifDto().setAudiosDtos(null);
            ctDto.getDispositifDto().setContactsDto(null);
        });


        return contactMapper.mapContactUrgenceList(contacts);
    }

    /*
     * Obtenir un contact
     */

    public ContactUrgenceDto getContactById(String id){
        ContactUrgence ct = contactUrgenceRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("ID ("+id+") contact introuvable"));

        return contactMapper.toContactUrgenceDto(ct);
    }

}