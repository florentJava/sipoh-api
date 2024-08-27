package com.sipoh.dispositif.mapper;

import org.mapstruct.Mapper;
import com.sipoh.dispositif.dtos.UserDto;
import com.sipoh.dispositif.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface Usermapper {

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);
    
}
