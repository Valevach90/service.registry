package com.andersen.banking.service.registry.meeting_impl.service.local.impl;

import com.andersen.banking.service.registry.meeting_db.entities.AddressEntity;
import com.andersen.banking.service.registry.meeting_db.repositories.AddressRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    public AddressEntity findById(Long addressId) {
        log.debug("Find addressEntity by id: {}", addressId);
        AddressEntity result = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));
        log.debug("Return addressEntity: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public AddressEntity findAddressByUserId(Long userId) {
        log.debug("Find addressEntity by userId: {}", userId);
        AddressEntity result = addressRepository.findAddressByUserId(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));
        log.debug("Return addressEntity: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressEntity> findAllAddress() {
        log.debug("Find all addressEntity");
        List<AddressEntity> result = addressRepository.findAll();
        log.debug("Return list size of addressEntity: {}", result.size());
        return result;
    }

    @Override
    @Transactional
    public AddressEntity save(AddressEntity address) {
        log.debug("Trying to save AddressEntity with address: {}", address);

        AddressEntity result = addressRepository.save(address);

        log.debug("Return saved AddressEntity: {}", result);
        return result;
    }
}
