package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.AuthController;
import com.andersen.banking.service.registry.meeting_api.dto.RegistrationDto;
import com.andersen.banking.service.registry.meeting_api.dto.TokenDto;
import com.andersen.banking.service.registry.meeting_impl.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    @Override
    public TokenDto refreshToken(String refreshToken) {
        log.trace("Refresh token");
        return authService.refreshToken(refreshToken);
    }

    @Override
    public void logout(String refreshToken) {
        authService.logoutUser(refreshToken);
    }
}
