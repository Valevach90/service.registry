package com.andersen.banking.service.registry.meeting_impl.service.local;


import com.andersen.banking.service.registry.meeting_db.entities.AddressEntity;

import java.util.List;

/**
 * Service for working with address.
 */
public interface AddressService {
    /**
     * Return address by id.
     *
     * @param addressId id of the address
     * @return AddressEntity
     */
    AddressEntity findById(Long addressId);

    /**
     * Return address by userId.
     *
     * @param userId userId of the address
     * @return AddressEntity
     */
    AddressEntity findAddressByUserId(Long userId);

    /**
     * Return all addresses.
     *
     * @return AddressEntity
     */
    List<AddressEntity> findAllAddress();

    /**
     * Save event.
     *
     * @param address saving address
     * @return saved address
     */
    AddressEntity save(AddressEntity address);
}
