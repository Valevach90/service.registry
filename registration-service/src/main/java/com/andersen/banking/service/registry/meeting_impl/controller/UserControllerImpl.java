package com.andersen.banking.service.registry.meeting_impl.controller;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.extractIdFromToken;
import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.extractLoginFromToken;

import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserCreateResponseDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserResponseDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserRequestDto;
import com.andersen.banking.service.registry.meeting_api.dto.user.UserUpdateEmailDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) {
        log.debug("Find all users");

        Page<UserResponseDto> result = userService.findAll(pageable)
                .map(userMapper::toUserDto);

        log.debug("Return list of userDto: {}", result.getContent());
        return result;
    }

    @Override
    public UserResponseDto findUser(Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(extractIdFromToken(jwt));
        log.debug("Find user for authentication user with id {}", id);

        UserResponseDto result = userMapper.toUserDto(userService.findById(id));

        log.info("Return userDto: {}", result);

        return result;
    }

    @Override
    public UserResponseDto create(UserCreateResponseDto userResponseDto) {
        log.debug("Try to create user: {}", userResponseDto);

        User user = userMapper.toUser(userResponseDto);

        User savedUser = userService.create(user);

        UserResponseDto savedUserResponseDto = userMapper.toUserDto(savedUser);

        log.debug("created user: {}", savedUserResponseDto);
        return savedUserResponseDto;
    }

    @Override
    public void updateUser(Authentication authentication, UserRequestDto userRequestDto) {
        log.debug("Try to update user: {}", userRequestDto);

        setAttributes(authentication, userRequestDto);
        User addressUpdated = userMapper.requestDtoToUser(userRequestDto);
        userService.update(addressUpdated);

        log.debug("Updated user success");
    }

    @Override
    public void updateUserEmail(Authentication authentication, UserUpdateEmailDto userUpdateEmailDto) {
        log.debug("Try to update user email: {}", userUpdateEmailDto);

        setAttributesForUpdateEmail(authentication, userUpdateEmailDto);
        User emailUpdateUser = userMapper.updateEmailRequestDtoToUser(userUpdateEmailDto);
        userService.updateEmail(emailUpdateUser);

        log.debug("Updated user email success");
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Try to delete user with id: {}", id);

        userService.deleteById(id);

        log.debug("Deleted user with id: {}", id);
    }

    private void setAttributes(Authentication authentication, UserRequestDto userRequestDto) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(extractIdFromToken(jwt));
        userRequestDto.setId(id);
        userRequestDto.setUsername(extractLoginFromToken(jwt));
    }

    private void setAttributesForUpdateEmail(Authentication authentication, UserUpdateEmailDto userUpdateEmailDto) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(extractIdFromToken(jwt));
        userUpdateEmailDto.setId(id);
        userUpdateEmailDto.setUsername(extractLoginFromToken(jwt));
    }
}
