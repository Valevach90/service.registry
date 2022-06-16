package com.andersen.banking.service.registry.meeting_impl.service;

import org.springframework.stereotype.Service;

/**
 * Service for sending and confirmation notifications.
 */

@Service
public interface NotificationService {

    /**
     * Send Email notification.
     *
     * @param email
     */
    void sendEmailNotification(String email);

    /**
     * Confirm code received by Email notification.
     *
     * @param email
     * @param code
     * @return confirmation
     */
    Boolean confirmCodeReceivedByEmailNotification(String email, String code);
}