package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    /**
     * Block Email address for specified time.
     *
     * @param email
     */
    void blockEmailAddress(String email);

    /**
     * Check if Email address is blocked.
     *
     * @param email
     */
    Boolean isEmailAddressBlocked(String email);

    /**
     * Get notification by email.
     *
     * @param email
     */
    Optional<Notification> getNotification(String email);
}