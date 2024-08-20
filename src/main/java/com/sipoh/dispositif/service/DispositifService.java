package com.sipoh.dispositif.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.exception.GeneralException;
import com.sipoh.dispositif.mapper.DispositifMapper;
import com.sipoh.dispositif.repository.DispositifRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispositifService {

    private final DispositifRepo dispositifRepo;
    private final DispositifMapper dispoMapper;


    /*
     *  inserer un dispositif dans la bd.
     *
    */

    public DispositifDto create(DispositifDto dispoDto){

        

        // Traitement des donnees entrantes (OPTiONNEL)
        dispoDto.setAudiosDtos(null);
        dispoDto.setContactsDto(null);

        System.out.println(dispoDto);


        // Convertion du dto en entity et sauvegarde dans la BD
        Dispositif dispo = dispoMapper.toDispositif(dispoDto);
        dispositifRepo.save(dispo);
        dispoDto = dispoMapper.toDispositifDto(dispo);


        return dispoDto;
    }


    /*
     * Attribuer un dispositif a un utilisateur
     */
    public DispositifDto userToDispositif(String userId , String dispo_Id) {
        
        Optional<Dispositif> opDispo = dispositifRepo.findById(dispo_Id);

        if(!opDispo.isPresent()){
            throw new EntityNotFoundException("l'ID  du dispositif ( "+dispo_Id+" ) est invalid");
        }else if (!opDispo.get().getUtilisateurId().equals("0") ||opDispo.get().getUtilisateurId() !=null) {
            throw new GeneralException("Ce dispositif possede deja un utilisateur");
        }

        Dispositif dispo = opDispo.get();

        dispo.setUtilisateurId(userId);
        dispositifRepo.save(dispo);

        DispositifDto dispositifDto = dispoMapper.toDispositifDto(dispo);

        return dispositifDto;

    }

    /*
     * Recuperer tout les dispositifs d'un utilisateur
     */
    public List<DispositifDto> getUserDispositifs(String userId){

        List<Dispositif> dispositifs = dispositifRepo.findByUtilisateurId(userId);
        return dispoMapper.mapDispoList(dispositifs);
    }

    /*
     * 
     */

}