package com.andersen.banking.service.registry.meeting_impl.service.processing;


import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;

import java.util.List;

/**
 * Processing Service for working with addresses.
 */
public interface AddressProcessingService {
    /**
     * Return address by id.
     *
     * @param addressId id of the address
     * @return AddressDto
     */
    AddressDto findById(Long addressId);

    /**
     * Return address by userId.
     *
     * @param userId userId of the address
     * @return AddressEntity
     */
    AddressDto findAddressByUserId(Long userId);

    /**
     * Return all addresses.
     *
     * @return AddressDto
     */
    List<AddressDto> findAllAddresses();

    /**
     * Update address.
     *
     * @param id                 id of address to change.
     * @param addressModifyDto dto for update address.
     */
    void updateAddress(Long id, AddressModifyDto addressModifyDto);
}
