package com.sipoh.dispositif.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.EnregistrementAudio;

@Repository
public interface EnregistrementAudioRepo extends JpaRepository<EnregistrementAudio,String> {
    List<EnregistrementAudio> findByDispositif(Dispositif dispo);
}
