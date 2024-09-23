package com.sipoh.dispositif.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sipoh.dispositif.dtos.ModelDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.Model;


@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "dispositifs", target = "dispositifIds" , qualifiedByName = "mapModelList")
    @Named("toModelDto")
    ModelDto toModelDto(Model model);


    @Mapping(source = "dispositifIds", target = "dispositifs" , qualifiedByName = "mapToModelList")
    @Named("toModel")
    Model toModel(ModelDto modelDto);


    @Named("mapModelList")
    default List<String> mapModelList(List<Dispositif> dispositifs) {
        return dispositifs.stream().map(dispositif -> dispositif.getId()).toList();
    }

    @Named("mapToModelList")
    default List<Dispositif> mapToModelList(List<String> dispositifIds) {

        if (dispositifIds == null) {
            return List.of();
            
        }

        return dispositifIds.stream().map(
            dispositifId -> Dispositif.builder()
                                    .id(dispositifId).build()
        ).toList();
    }


    
    
} 
