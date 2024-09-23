package com.sipoh.dispositif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sipoh.dispositif.entity.Fournisseur;

@Repository
public interface FournisseurRepo extends JpaRepository<Fournisseur , String> {
    
}
