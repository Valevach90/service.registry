package com.andersen.banking.meeting_db.repository;


import com.andersen.banking.meeting_db.entity.Notification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
