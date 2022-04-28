package com.andersen.banking.service.registry.meeting_impl.service.local.impl;

import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;

    @Override
    public PassportEntity findById(Long id) {
        log.debug("Find passport by id: {}", id);

        PassportEntity passportEntity = passportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));

        log.debug("Found passport: {} by id: {}", passportEntity, id);
        return passportEntity;
    }

    @Override
    public PassportEntity findByUserId(Long userId) {
        log.debug("Find passport by id: {}", userId);

        PassportEntity passportEntity = passportRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));

        log.debug("Found passport: {} by user id: {}", passportEntity, userId);
        return passportEntity;
    }

    @Override
    public PassportEntity findByAddressId(Long addressId) {
        log.debug("Find passport by address id: {}", addressId);

        PassportEntity passportEntity = passportRepository.findByAddressId(addressId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));

        log.debug("Found passport: {} by address id: {}", passportEntity, addressId);
        return passportEntity;
    }

    @Override
    public Page<PassportEntity> findAll(Pageable pageable) {
        log.debug("Find all passports for pageable: {}", pageable);

        Page<PassportEntity> allPassports = passportRepository.findAll(pageable);

        log.debug("Found {} passports", allPassports.getContent().size());
        return allPassports;
    }
}
