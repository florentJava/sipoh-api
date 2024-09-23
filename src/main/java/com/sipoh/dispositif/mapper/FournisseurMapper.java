package com.sipoh.dispositif.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sipoh.dispositif.dtos.FournisseurDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.Fournisseur;


@Mapper(componentModel = "spring")
public interface FournisseurMapper {

    @Mapping(source = "dispositifs", target = "dispositifIds" , qualifiedByName = "mapFournilList")
    @Named("toFournisseurDto")
    FournisseurDto toFournisseurDto(Fournisseur fournisseur);


    @Mapping(source = "dispositifIds", target = "dispositifs" , qualifiedByName = "mapToFournilList")
    @Named("toFournisseur")
    Fournisseur toFournisseur(FournisseurDto fournisseurDto);


    @Named("mapFournilList")
    default List<String> mapFournisseurlList(List<Dispositif> dispositifs) {
        return dispositifs.stream().map(dispositif -> dispositif.getId()).toList();
    }

    @Named("mapToFournilList")
    default List<Dispositif> mapToFournilList(List<String> dispositifIds) {

        if (dispositifIds == null) {
            return List.of();
            
        }

        return dispositifIds.stream().map(
            dispositifId -> Dispositif.builder()
                                    .id(dispositifId).build()
        ).toList();
    }


    
}
