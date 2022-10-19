package com.andersen.banking.service.registry.meeting_impl.mapping;


import com.andersen.banking.service.registry.meeting_api.dto.user.UserCreateResponseDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserResponseDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserRequestDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserUpdateEmailDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper user.
 */

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toUserDto(User user);

    User toUser(UserResponseDto userResponseDto);

    @Mapping(target = "id", ignore = true)
    User toUser(UserCreateResponseDto userResponseDto);

    User requestDtoToUser(UserRequestDto userRequestDto);

    User updateEmailRequestDtoToUser(UserUpdateEmailDto userUpdateEmailDto);
}

