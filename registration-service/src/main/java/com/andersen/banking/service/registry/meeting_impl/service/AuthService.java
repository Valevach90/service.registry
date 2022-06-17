package com.andersen.banking.service.registry.meeting_impl.service;

import org.springframework.stereotype.Service;

/**
 * Authentication Service for working with Keycloak REST API.
 *
 * @author Siarhei Yurevich
 * @version 1.0
 */

@Service
public interface AuthService {

    /**
     * Add role UNAUTHORIZED to user
     *
     * @param id
     */
    void addRoleUnauthorized(String id);

    /**
     * Add role USER to user
     *
     * @param id
     */
    void addRoleUser(String id);

    /**
     * Add role ADMIN to user
     *
     * @param id
     */
    void addRoleAdmin(String id);

    /**
     * Add user
     *
     * @param userInJson
     */
    void addUser(String userInJson);

    /**
     * Set up new password
     *
     * @param id
     * @param newPassword
     */
    void setUpNewPassword(String id, String newPassword);
}
