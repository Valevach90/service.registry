package com.andersen.banking.service.registry.meeting_impl.mapping;


import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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

    default Page<UserDto> toListDtoUsers(Page<User> users) {

        Pageable pageable = users.getPageable();
        long total = users.getTotalElements();

        List<User> listUsers = users.getContent();
        List<UserDto> listDto = new ArrayList<>();

        for (User user : listUsers){
            listDto.add(toUserDto(user));
        }
        Page<UserDto> page = new PageImpl<>(listDto, pageable, total);

        return page;
    }
}
