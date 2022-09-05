package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Find notification by email.
     *
     * @param email
     * @return Notification for selected email
     */
    Optional<Notification> findByEmail(String email);
}
