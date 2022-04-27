package com.andersen.banking.service.registry.meeting_impl.mapping;


import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
import com.andersen.banking.service.registry.meeting_impl.config.MeetingMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper user.
 */

@Mapper(config = MeetingMapperConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserDto toUserDto(UserEntity user);

    void updateUserDetails(@MappingTarget UserEntity oldUser, UserEntity newUser);

}
