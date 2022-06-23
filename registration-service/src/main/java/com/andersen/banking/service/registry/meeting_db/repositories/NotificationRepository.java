package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Find notification by email.
     *
     * @param email
     * @return Notification for selected email
     */
    Optional<Notification> findByEmail(String email);
}
