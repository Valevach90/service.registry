package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to work with Passport.
 */
@Repository
public interface PassportRepository extends JpaRepository<PassportEntity, Long> {

    /**
     * Find passport of user.
     *
     * @param userId Id of user
     * @return Passport of selected user
     */
    Optional<PassportEntity> findByUserId(Long userId);

    /**
     * Find passport of user in selected address.
     *
     * @param addressId Id of address
     * @return Passport for selected address
     */
    Optional<PassportEntity> findByAddressId(Long addressId);
}
