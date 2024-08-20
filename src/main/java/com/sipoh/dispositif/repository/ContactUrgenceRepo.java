package com.sipoh.dispositif.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.sipoh.dispositif.entity.ContactUrgence;
import com.sipoh.dispositif.entity.Dispositif;


@Repository
public interface ContactUrgenceRepo extends JpaRepository<ContactUrgence , String>  {

    List<ContactUrgence> findByDispositif(Dispositif dispo);

}