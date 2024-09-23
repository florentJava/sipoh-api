package com.sipoh.dispositif.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sipoh.dispositif.entity.Model;


@Repository
public interface ModelRepo extends JpaRepository<Model , String> {

    
}
