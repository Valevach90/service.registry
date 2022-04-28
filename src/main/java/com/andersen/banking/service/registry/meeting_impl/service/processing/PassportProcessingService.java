package com.andersen.banking.service.registry.meeting_impl.service.processing;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassportProcessingService {

    /**
     * Find passport by Id.
     *
     * @param id Id of passport
     * @return Passport
     */
    PassportDto findById(Long id);

    /**
     * Find passport by user Id.
     *
     * @param userId Id of user
     * @return Passport
     */
    PassportDto findByUserId(Long userId);

    /**
     * Find passport by address Id.
     *
     * @param addressId Id of address
     * @return Passport
     */
    PassportDto findByAddressId(Long addressId);

    /**
     * Find all passports.
     *
     * @param pageable page object
     * @return page of passports
     */
    Page<PassportDto> findAll(Pageable pageable);
}
