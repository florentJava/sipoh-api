package com.sipoh.dispositif.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.dtos.EnregistrementAudioDto;
import com.sipoh.dispositif.entity.Dispositif;
import com.sipoh.dispositif.entity.EnregistrementAudio;


@Mapper(componentModel = "spring")
public interface EnregistrementAudioMapper {

    
    @Mapping(source = "dispositif", target = "dispositifDto", qualifiedByName = "dispoToDto")
    EnregistrementAudioDto toAudioDto(EnregistrementAudio audio);

    @Named("dispoToDto")
    @Mapping(source = "contacts" ,target="contactsDto",  ignore = true)
    @Mapping(source = "audios" ,target="audiosDtos", ignore = true)
    DispositifDto dispoToDto(Dispositif dispositif);

    @Mapping(source = "dispositifDto", target = "dispositif", qualifiedByName = "dispoToEntity")
    EnregistrementAudio toAufioEntity(EnregistrementAudioDto audioDto);

    @Named("dispoToEntity")
    @Mapping(source = "contactsDto" ,target="contacts",  ignore = true)
    @Mapping(source = "audiosDtos" ,target="audios", ignore = true)
    Dispositif dispoToEntity(DispositifDto dispositifDto);

    @Named("mapAudioList")
    List<EnregistrementAudioDto> mapAudioList(List<EnregistrementAudio> audios);

    @Named("mapAudioDtosList")
    List<EnregistrementAudio> mapAudioDtoList(List<EnregistrementAudioDto> audiosdto);

}