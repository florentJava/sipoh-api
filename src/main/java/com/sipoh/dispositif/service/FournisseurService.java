package com.sipoh.dispositif.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.FournisseurDto;
import com.sipoh.dispositif.entity.Fournisseur;
import com.sipoh.dispositif.mapper.FournisseurMapper;
import com.sipoh.dispositif.repository.FournisseurRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FournisseurService {


    private final FournisseurRepo fournisseurRepo;
    private final FournisseurMapper fournisseurMapper;

    public FournisseurDto create(FournisseurDto fournisseurDto) {

        Fournisseur fournisseur = fournisseurMapper.toFournisseur(fournisseurDto);
        return fournisseurMapper.toFournisseurDto(fournisseurRepo.save(fournisseur));
    }


    @Transactional
    public List<FournisseurDto> getAll() {
        return fournisseurRepo.findAll().stream().map(fournisseurMapper::toFournisseurDto).toList();
    }
    
}
