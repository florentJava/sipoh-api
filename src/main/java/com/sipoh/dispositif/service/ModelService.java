package com.sipoh.dispositif.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sipoh.dispositif.dtos.ModelDto;
import com.sipoh.dispositif.entity.Model;
import com.sipoh.dispositif.mapper.ModelMapper;
import com.sipoh.dispositif.repository.ModelRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepo modelRepo;
    private final ModelMapper modelMapper;

    public ModelDto create(ModelDto modelDto) {

        Model model = modelMapper.toModel(modelDto);
        return modelMapper.toModelDto(modelRepo.save(model));
    }

    @Transactional
    public List<ModelDto> getAll() {
        return modelRepo.findAll().stream().map(modelMapper::toModelDto).toList();
    }
    
    
}
