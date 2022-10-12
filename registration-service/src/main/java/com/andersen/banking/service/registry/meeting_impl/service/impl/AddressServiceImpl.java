package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.repositories.AddressRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address create(Address address) {
        log.debug("Creating new address: {}", address);

        Address saveAddress = addressRepository.save(address);

        log.debug("Created new address: {}", saveAddress);

        return saveAddress;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findById(Long addressId) {
        log.debug("Find address by id: {}", addressId);

        Optional<Address> result = addressRepository.findById(addressId);

        log.debug("Return address: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findAddressByUserId(UUID userId) {
        log.debug("Find address by userId: {}", userId);

        Optional<Address> result = addressRepository.findAddressByUserId(userId);

        log.debug("Return address: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAllAddresses() {
        log.debug("Find all address");

        List<Address> result = addressRepository.findAll();

        log.debug("Return list of address: {}", result);
        return result;
    }

    @Override
    @Transactional
    public void update(Address updatedAddress) {
        log.debug("Trying to update Address: {}", updatedAddress);

        Address address = addressRepository.findById(updatedAddress.getId())
                .orElseThrow(() -> new NotFoundException(Address.class, updatedAddress.getId()));

        updatedAddress.setUserId(address.getUserId());

        addressRepository.save(updatedAddress);

        log.debug("Return updated Address: {}", updatedAddress);
    }
}
