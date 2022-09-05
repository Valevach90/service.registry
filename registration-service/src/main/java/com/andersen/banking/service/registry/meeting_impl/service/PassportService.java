package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service for working with Passports.
 */
public interface PassportService {

    /**
     * Find passport by Id.
     *
     * @param id Id of passport
     * @return Passport
     */
    Optional<Passport> findById(Long id);

    /**
     * Find passport by user Id.
     *
     * @param userId Id of user
     * @return Passport
     */
    Optional<Passport> findByUserId(UUID userId);

    /**
     * Find passport by address Id.
     *
     * @param addressId Id of address
     * @return Passport
     */
    Optional<Passport> findByAddressId(Long addressId);

    /**
     * Find all passports.
     *
     * @param pageable page object
     * @return page of passports
     */
    Page<Passport> findAll(Pageable pageable);

    /**
     * Update passport.
     *
     * @param passport passport entity to update
     */
    void update(Passport passport);

    /**
     * Delete passport by Id.
     *
     * @param id Id of passport
     */
    void deleteById(Long id);

    /**
     * Create new passport.
     *
     * @param passport passport to create
     * @return passport
     */
    Passport create(Passport passport, UUID userId, Long addressId);
}
