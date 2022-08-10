package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AuthController;
import com.andersen.banking.service.registry.meeting_impl.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

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
    public void auth(Jwt jwt) {

        String id = extractIdFromToken(jwt);
        String login = extractLoginFromToken(jwt);

        Map<String, Object> resource_access = (Map<String, Object>) jwt.getClaims().get("resource_access");
        resource_access.remove("api-gateway");

        log.trace("User authorization: login " + login + ", id " + id);

        if (doesUserHaveNoRoles(jwt)) {

            if (authService.isUserRegisteredInExternalBank(login)) {
                authService.addRoleUser(id);
            } else {
                authService.addRoleUnauthorized(id);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already authenticated");
        }
        log.trace("User authorized: login " + login + ", id " + id);
    }

    @Override
    public void resetPassword(Jwt jwt, String newPassword) {

        String id = extractIdFromToken(jwt);

        log.trace("Setting new password, user id {} ", id);

        authService.resetPassword(id, newPassword);

        log.trace("Set new password, user id {} ", id);
    }
}
