package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to work with Passport.
 */
public interface PassportRepository extends JpaRepository<Passport, Long> {

    /**
     * Find passport of user.
     *
     * @param userId Id of user
     * @return Passport of selected user
     */
    Optional<Passport> findByUserId(UUID userId);

    /**
     * Find passport of user in selected address.
     *
     * @param addressId Id of address
     * @return Passport for selected address
     */
    Optional<Passport> findByAddressId(Long addressId);
}
