package com.andersen.banking.service.registry.meeting_impl.service;


import com.andersen.banking.service.registry.meeting_db.entities.Address;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for working with address.
 */
public interface AddressService {

    /**
     * Create address
     *
     * @param address - parameters for new address
     * @return Address
     */
    Address create(Address address);

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
    Optional<Address> findAddressByUserId(UUID userId);

    /**
     * Return all addresses.
     *
     * @return list of addresses
     */
    List<Address> findAllAddresses();

    /**
     * Update address.
     *
     * @param updateAddress update address
     */
    void update(Address updateAddress);
}
