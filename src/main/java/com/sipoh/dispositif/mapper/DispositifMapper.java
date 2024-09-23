package com.sipoh.dispositif.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.entity.Dispositif;

@Mapper(componentModel = "spring" , uses = {ContactMapper.class, EnregistrementAudioMapper.class , ModelMapper.class , FournisseurMapper.class} )
public interface DispositifMapper {

    @Mapping(source = "contacts", target = "contactsDto" , qualifiedByName = "mapContactUrgenceList")
    @Mapping(source = "audios", target = "audiosDtos" , qualifiedByName = "mapAudioList")
    @Mapping(source = "model", target = "modelDto", qualifiedByName = "toModelDto")
    @Mapping(source = "fournisseur", target = "fournisseurDto", qualifiedByName = "toFournisseurDto")
    DispositifDto toDispositifDto(Dispositif dispositif);

    @Mapping(source = "contactsDto", target = "contacts" , qualifiedByName = "mapContactUrgenceDtoList")
    @Mapping(source = "audiosDtos", target = "audios" , qualifiedByName = "mapAudioDtosList")
    @Mapping(source = "modelDto", target = "model", qualifiedByName = "toModel")
    @Mapping(source = "fournisseurDto", target = "fournisseur", qualifiedByName = "toFournisseur")
    Dispositif toDispositif(DispositifDto dispositifDto);

    List<DispositifDto> mapDispoDtosList(List<DispositifDto> dispositifDto);

    List<DispositifDto> mapDispoList(List<Dispositif> dispositif);
    




}
