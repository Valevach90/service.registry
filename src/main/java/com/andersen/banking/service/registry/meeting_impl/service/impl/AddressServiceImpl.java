package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.repositories.AddressRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

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
    public Optional<Address> findAddressByUserId(Long userId) {
        log.debug("Find address by userId: {}", userId);
        Optional<Address> result = addressRepository.findAddressByUserId(userId);
        log.debug("Return address: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAllAddress() {
        log.debug("Find all address");
        List<Address> result = addressRepository.findAll();
        log.debug("Return list size of address: {}", result.size());
        return result;
    }

    @Override
    @Transactional
    public void update(Address address) {
        log.debug("Trying to update Address: {}", address);
        Address result = addressRepository.findById(address.getId())
                .orElseThrow(() -> new NotFoundException(Address.class, address.getId()));

        addressRepository.save(result);

        log.debug("Return updated Address: {}", result);
    }
}
