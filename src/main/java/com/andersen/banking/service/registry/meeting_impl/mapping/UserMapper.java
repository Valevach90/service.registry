package com.andersen.banking.service.registry.meeting_impl.mapping;


import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
import com.andersen.banking.service.registry.meeting_impl.config.MeetingMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;

/**
 * Mapper user.
 */

@Mapper(config = MeetingMapperConfig.class)
public interface UserMapper {

  
    UserDto toUserDto(UserEntity user);
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDto user);

    void updateUserDetails(@MappingTarget Optional<UserEntity> oldUser, UserEntity newUser);
}
