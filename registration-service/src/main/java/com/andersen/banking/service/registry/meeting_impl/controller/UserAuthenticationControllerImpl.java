package com.andersen.banking.service.registry.meeting_impl.controller;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.extractEmailFromToken;
import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.extractIdFromToken;

import com.andersen.banking.service.registry.meeting_api.controller.UserAuthenticationController;
import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAuthenticationControllerImpl implements
        UserAuthenticationController {

    private final UserController userController;

    @Override
    public UserDto findUser(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(extractIdFromToken(jwt));
        log.debug("Find user for authentication user with id {}", id);
        return userController.findById(id);
    }

    @Override
    public UserDto create(Authentication authentication, UserDto userDto) {
        setIdAndMail(authentication, userDto);
        log.debug("Create authentication user with id {}", userDto.getId());
        return userController.create(userDto);
    }

    @Override
    public void updateUser(Authentication authentication, UserDto userDto) {
        setIdAndMail(authentication, userDto);
        log.debug("Update authentication user with id {}", userDto.getId());
        userController.updateUser(userDto);
    }

    @Override
    public void deleteById(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(extractIdFromToken(jwt));
        log.debug("Delete authentication user with id {}", id);
        userController.deleteById(id);
    }

    private void setIdAndMail(Authentication authentication, UserDto userDto) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UUID id = UUID.fromString(extractIdFromToken(jwt));
        userDto.setId(id);
        userDto.setEmail(extractEmailFromToken(jwt));
    }
}
