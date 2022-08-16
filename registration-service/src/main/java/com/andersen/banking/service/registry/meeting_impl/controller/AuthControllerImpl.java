package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AuthController;
import com.andersen.banking.service.registry.meeting_api.dto.RegistrationDto;
import com.andersen.banking.service.registry.meeting_impl.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.*;


/**
 * Authentication controller implementation.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    @Autowired
    AuthService authService;

    @Override
    public RegistrationDto auth(Jwt jwt) {

        String id = extractIdFromToken(jwt);
        String login = extractLoginFromToken(jwt);

        log.trace("User authorization: login " + login + ", id " + id);

        if (doesUserHaveNoRoles(jwt)) {
            authService.addRoleUnauthorized(id);
        }
        if (doesUserHaveUserRoles(jwt)) {
            return new RegistrationDto(true);
        }
        log.trace("User authorized: login " + login + ", id " + id);
        return new RegistrationDto(false);
    }

    @Override
    public void resetPassword(Jwt jwt, String newPassword) {

        String id = extractIdFromToken(jwt);

        log.trace("Setting new password, user id {} ", id);

        authService.resetPassword(id, newPassword);
        authService.addRoleUser(id);

        log.trace("Set new password, user id {} ", id);
    }
}
