package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AuthController;
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
    public void auth(Jwt jwt) {

        String id = extractIdFromToken(jwt);
        String login = extractLoginFromToken(jwt);

        log.trace("User authorization: login " + login + ", id " + id);

        if (doesUserHaveNoRoles(jwt)) {

            if (false /*authService.isUserRegisteredInExternalBank(login)*/) {
                authService.addRoleUser(id);
            } else {
                authService.addRoleUnauthorized(id);
            }

        }
        log.trace("User authorized: login " + login + ", id " + id);
    }
}
