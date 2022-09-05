package com.andersen.banking.meeting_db.repository;


import com.andersen.banking.meeting_db.entity.RegistrationNotification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<RegistrationNotification, Long> {

    /**
     * Find notification by email.
     *
     * @param email
     * @return Notification for selected email
     */
    Optional<RegistrationNotification> findByEmail(String email);
}
