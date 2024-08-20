package com.sipoh.dispositif.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.sipoh.dispositif.dtos.EnregistrementAudioDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.EnregistrementAudio;
import com.sipoh.dispositif.mapper.EnregistrementAudioMapper;
import com.sipoh.dispositif.repository.DispositifRepo;
import com.sipoh.dispositif.repository.EnregistrementAudioRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final EnregistrementAudioRepo audioRepo;
    private final EnregistrementAudioMapper audioMapper;

    private final DispositifRepo dispositifRepo;
    



    /*
     * Sauvegarde des audios
     */

    public EnregistrementAudioDto create(EnregistrementAudioDto audioDto , String dispoId){

        Dispositif dispo = dispositifRepo
                        .findById(dispoId)
                        .orElseThrow(() -> new EntityNotFoundException("Identifiant ("+dispoId+") de dispositif  introuvable ") );


        EnregistrementAudio audio =  audioMapper.toAufioEntity(audioDto);

        audio.setDispositif(dispo);
        audioRepo.save(audio);

        return audioMapper.toAudioDto(audio);
        
    }

    /*
     * suppression des audios
     */

    public void deleteAudio(String audioId){
        audioRepo.deleteById(audioId);
    }

    /*
     * Recuperations des audios d'un dispositif
     */

    public List<EnregistrementAudioDto> getUserAudios(String dispoId){

        Dispositif dispo = dispositifRepo
                        .findById(dispoId)
                        .orElseThrow(() -> new EntityNotFoundException("ID de dispositif "+dispoId+" Invalid."));


        List<EnregistrementAudio> audios = audioRepo.findByDispositif(dispo);
        return audioMapper.mapAudioList(audios);
    }


    


    
}
