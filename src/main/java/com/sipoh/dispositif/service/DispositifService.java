package com.sipoh.dispositif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.Fournisseur;
import com.sipoh.dispositif.entity.Model;
import com.sipoh.dispositif.entity.enumeration.DispositifStatut;
import com.sipoh.dispositif.exception.GeneralException;
import com.sipoh.dispositif.mapper.DispositifMapper;
import com.sipoh.dispositif.repository.DispositifRepo;
import com.sipoh.dispositif.repository.FournisseurRepo;
import com.sipoh.dispositif.repository.ModelRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispositifService {

    private final DispositifRepo dispositifRepo;
    private final DispositifMapper dispoMapper;
    private final FournisseurRepo fournisseurRepo;
    private final ModelRepo modelRepo;


    /*
     *  inserer un dispositif dans la bd.
     *
    */

    @Transactional
    public DispositifDto create(DispositifDto dispoDto){

        

        // Traitement des donnees entrantes (OPTiONNEL)
        dispoDto.setAudiosDtos(null);
        dispoDto.setContactsDto(null);

        dispoDto.setStatut(DispositifStatut.ATTENTE);

        // Convertion du dto en entity et sauvegarde dans la BD
        Dispositif dispo = dispoMapper.toDispositif(dispoDto);

        Fournisseur fournisseur = fournisseurRepo.findById(dispoDto.getFournisseurDto().getId()).orElseThrow(() -> new EntityNotFoundException("Fournisseur not found : ID = "+ dispo.getFournisseur().getId()));
        Model model =  modelRepo.findById(dispoDto.getModelDto().getId()).orElseThrow(() -> new EntityNotFoundException("Model not found : ID = " + dispo.getModel().getId()));
        
        dispo.setFournisseur(fournisseur);
        dispo.setModel(model);
        dispositifRepo.save(dispo);
        dispoDto = dispoMapper.toDispositifDto(dispo);


        return dispoDto;
    }


    /*
     * Attribuer un dispositif a un utilisateur
     */
    @Transactional
    public DispositifDto userToDispositif(String userId , String dispo_Id) {
        
        Optional<Dispositif> opDispo = dispositifRepo.findById(dispo_Id);

        if(!opDispo.isPresent()){
            throw new EntityNotFoundException("l'ID  du dispositif ( "+dispo_Id+" ) est invalid");
        }else if (opDispo.get().getUtilisateurId() != null ) {
            throw new GeneralException("Ce dispositif possede deja un utilisateur");
        }

        Dispositif dispo = opDispo.get();

        dispo.setUtilisateurId(userId);
        dispo.setStatut(DispositifStatut.SERVICE);
        dispositifRepo.save(dispo);

        DispositifDto dispositifDto = dispoMapper.toDispositifDto(dispo);

        return dispositifDto;

    }

    /*
     * Recuperer tout les dispositifs d'un utilisateur
     */
    @Transactional
    public List<DispositifDto> getUserDispositifs(String userId){

        List<Dispositif> dispositifs = dispositifRepo.findByUtilisateurId(userId);
        return dispoMapper.mapDispoList(dispositifs);
    }

    /*
     * Recuperer la liste de tous les dispositifs
     */
    @Transactional
    public List<DispositifDto> getAllDispo(){
        
        List<Dispositif> dispos = dispositifRepo.findAll();
        List<DispositifDto> dispoDtos = dispoMapper.mapDispoList(dispos);
        
        return dispoDtos;
    }

    /*
     * recuperer un dispositif a partir de son ID
     */
    @Transactional
    public DispositifDto getIspoById(String dispoId){

        Dispositif dispo = dispositifRepo.findById(dispoId).orElseThrow(() -> new EntityNotFoundException("Dispositif not fount : ID = "+dispoId));

        if(dispo.getContacts() != null)
        dispo.getContacts().forEach(ct ->{
            ct.setDispositif(null);
        });

        if(dispo.getAudios() != null){
            dispo.getAudios().forEach(
                ad ->{
                    ad.setDispositif(null);
                }
            );
        }

        return dispoMapper.toDispositifDto(dispo);
    }


    /* update le statut dispositif */
    @Transactional
    public DispositifDto setDispositifStatut(String dispoId,String statut) {
        
        Dispositif dispo = dispositifRepo.findById(dispoId).orElseThrow(() -> new EntityNotFoundException("Dispositif not fount : ID = "+dispoId));
        dispo.setStatut(DispositifStatut.valueOf(statut));
        dispositifRepo.save(dispo);

        return dispoMapper.toDispositifDto(dispo);
    }


}