package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.PersonalDataController;
import com.andersen.banking.service.registry.meeting_api.dto.PersonalDataDto;
import com.andersen.banking.service.registry.meeting_impl.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.extractEmailFromToken;

/**
 * Personal Data controller implementation.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class PersonalDataControllerImpl implements PersonalDataController {

    private final PersonalDataService personalDataService;

    @Override
    public PersonalDataDto getUserPersonalData(Jwt jwt) {

        String email = extractEmailFromToken(jwt);

        log.trace("Find user personal data by user email: {}", email);

        PersonalDataDto personalDataDto = personalDataService.getPersonalData(email);

        log.trace("Get user personal data: {}", personalDataDto);

        return personalDataDto;
    }

    @Override
    public void updateUserPersonalData(PersonalDataDto personalDataDto) {

        log.trace("Update user personal data: {}", personalDataDto);

        personalDataService.updatePersonalData(personalDataDto);

        log.trace("Updated user personal data: {}", personalDataDto);
    }
}
