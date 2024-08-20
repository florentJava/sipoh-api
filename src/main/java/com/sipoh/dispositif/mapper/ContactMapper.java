package com.sipoh.dispositif.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sipoh.dispositif.dtos.ContactUrgenceDto;
import com.sipoh.dispositif.dtos.DispositifDto;
import com.sipoh.dispositif.entity.ContactUrgence;
import com.sipoh.dispositif.entity.Dispositif;


@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(source = "dispositif", target = "dispositifDto", qualifiedByName = "dispoToDto")
    ContactUrgenceDto toContactUrgenceDto(ContactUrgence contactUrgence);

    @Named("dispoToDto")
    @Mapping(source = "contacts" ,target="contactsDto",  ignore = true)
    @Mapping(source = "audios" ,target="audiosDtos", ignore = true)
    DispositifDto dispoToDto(Dispositif dispositif);

    @Mapping(source = "dispositifDto", target = "dispositif", qualifiedByName = "dispoToEntity")
    ContactUrgence toContactUrgence(ContactUrgenceDto contactUrgenceDto);

    @Named("dispoToEntity")
    @Mapping(source = "contactsDto" ,target="contacts",  ignore = true)
    @Mapping(source = "audiosDtos" ,target="audios", ignore = true)
    Dispositif dispoToEntity(DispositifDto dispositif);

    @Named("mapContactUrgenceList")
    List<ContactUrgenceDto> mapContactUrgenceList(List<ContactUrgence> contacts);

    @Named("mapContactUrgenceDtoList")
    List<ContactUrgence> mapContactUrgenceDtoList(List<ContactUrgenceDto> contactsDto);

}