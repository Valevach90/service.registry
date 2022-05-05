package com.andersen.banking.service.registry.meeting_impl.mapping;


import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper user.
 */

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "patronymic", target = "patronymic"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone")
    })
    UserDto toUserDto(User user);

    List<UserDto> toListDtoUsers(List<User> user);
}
