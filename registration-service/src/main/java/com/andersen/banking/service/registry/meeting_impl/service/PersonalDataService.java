package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_api.dto.PersonalDataDto;

/**
 * Service for working with user personal data.
 */
public interface PersonalDataService {

    /**
     * Get user personal data by user email.
     *
     * @param email user Email
     * @return PersonalDataDto
     */
    PersonalDataDto getPersonalData(String email);

    /**
     * Update user personal data.
     *
     * @param personalDataDto user PersonalDataDto
     */
    void updatePersonalData(PersonalDataDto personalDataDto);
}
