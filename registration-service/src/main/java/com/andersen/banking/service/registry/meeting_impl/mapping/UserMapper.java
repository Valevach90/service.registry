package com.andersen.banking.service.registry.meeting_impl.mapping;


import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper user.
 */

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}

