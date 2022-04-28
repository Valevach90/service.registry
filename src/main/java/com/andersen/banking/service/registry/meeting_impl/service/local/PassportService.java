package com.andersen.banking.service.registry.meeting_impl.service.local;

import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    PassportEntity findById(Long id);

    /**
     * Find passport by user Id.
     *
     * @param userId Id of user
     * @return Passport
     */
    PassportEntity findByUserId(Long userId);

    /**
     * Find passport by address Id.
     *
     * @param addressId Id of address
     * @return Passport
     */
    PassportEntity findByAddressId(Long addressId);

    /**
     * Find all passports.
     *
     * @param pageable page object
     * @return page of passports
     */
    Page<PassportEntity> findAll(Pageable pageable);
}
