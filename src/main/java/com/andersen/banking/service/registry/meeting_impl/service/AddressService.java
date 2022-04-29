package com.andersen.banking.service.registry.meeting_impl.service;


import com.andersen.banking.service.registry.meeting_db.entities.Address;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with address.
 */
public interface AddressService {

    /**
     * Return address by id.
     *
     * @param addressId id of the address
     * @return Address
     */
    Optional<Address> findById(Long addressId);

    /**
     * Return address by userId.
     *
     * @param userId userId of the address
     * @return Address
     */
    Optional<Address> findAddressByUserId(Long userId);

    /**
     * Return all addresses.
     *
     * @return Address
     */
    List<Address> findAllAddress();

    /**
     * Update address.
     *
     * @param address update address
     */
    void update(Address address);
}
