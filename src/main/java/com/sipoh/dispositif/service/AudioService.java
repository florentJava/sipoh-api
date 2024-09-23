package com.sipoh.dispositif.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sipoh.dispositif.dtos.EnregistrementAudioDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.EnregistrementAudio;
import com.sipoh.dispositif.exception.GeneralException;
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
    
    private final String storageLocation = System.getProperty("user.home") + "/Documents/osc/fileSave/";

    /*
     * Sauvegarde des audios
     */

    public EnregistrementAudioDto create(EnregistrementAudioDto audioDto , String dispoId , MultipartFile file) throws IOException {

        // Creer le dossier si necessaire
        File directory = new File(storageLocation);
        String extension = getFileExtension(file);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Recuperer le dispo dans la BD

        Dispositif dispo = dispositifRepo
                        .findById(dispoId)
                        .orElseThrow(() -> new EntityNotFoundException("Identifiant ("+dispoId+") de dispositif  introuvable ") );

        //Creer l'instance d'EnregitrementAudio              
        EnregistrementAudio audio =  audioMapper.toAufioEntity(audioDto);
        String uniqueFileName = UUID.randomUUID().toString()+extension;

        audio.setId(uniqueFileName);
        audio.setDispositif(dispo);
        audio.setAltitude(audioDto.getAltitude());
        audio.setLattitude(audioDto.getLattitude());
        audio.setLongitude(audioDto.getLongitude());
        audio.setLongitude(audioDto.getLongitude());

        audio.setAudioUrl(storageLocation+uniqueFileName);

        audioRepo.save(audio);

        Path filePath = Paths.get(storageLocation + uniqueFileName );

        Files.copy(file.getInputStream(), filePath);

        return audioMapper.toAudioDto(audio);
    }

    /*
     * Recuperation de l'extension du fichier
     */

    public String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return "."+originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return ""; // Retourne une chaîne vide si aucune extension n'est trouvée
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
        List<EnregistrementAudioDto> audioDtos =  audioMapper.mapAudioList(audios);
        
        
        audioDtos.forEach(ad -> {
            ad.setDispositifDto(null);
        });

        return audioDtos;
    }

    /*
     * Recuperer un audio par son ID
     */

    public Resource downloadAudio(String audioId)  {

        Path filePath = Paths.get(storageLocation).resolve(audioId).normalize();
        Resource resource;


        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();

            throw new GeneralException("Ressource Introuvable");
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        }else{
            throw new GeneralException("Fichier non lisible " + audioId);
        }
    }


    
    
}
