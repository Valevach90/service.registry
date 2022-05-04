package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;

    @Override
    public Optional<Passport> findById(Long id) {
        log.info("Find passport by id: {}", id);

        Optional<Passport> passportEntity = passportRepository.findById(id);

        log.info("Found passport: {} by id: {}", passportEntity, id);
        return passportEntity;
    }

    @Override
    public Optional<Passport> findByUserId(Long userId) {
        log.info("Find passport by id: {}", userId);

        Optional<Passport> passportEntity = passportRepository.findByUserId(userId);

        log.info("Found passport: {} by user id: {}", passportEntity, userId);
        return passportEntity;
    }

    @Override
    public Optional<Passport> findByAddressId(Long addressId) {
        log.info("Find passport by address id: {}", addressId);

        Optional<Passport> passportEntity = passportRepository.findByAddressId(addressId);

        log.info("Found passport: {} by address id: {}", passportEntity, addressId);
        return passportEntity;
    }

    @Override
    public Page<Passport> findAll(Pageable pageable) {
        log.info("Find all passports for pageable: {}", pageable);

        Page<Passport> allPassports = passportRepository.findAll(pageable);

        log.info("Found {} passports", allPassports.getContent().size());
        return allPassports;
    }

    @Override
    @Transactional
    public void update(Passport passportEntity) {
        log.info("Try to update passport: {}", passportEntity);

        Passport passport = passportRepository.findById(passportEntity.getId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));

        passportEntity.setUser(passport.getUser());
        passportEntity.setAddress(passport.getAddress());

        passportRepository.save(passportEntity);

        log.info("Updated passport : {}", passportEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting passport with id: {}", id);

        passportRepository.deleteById(id);

        log.info("deleted passport with id: {}", id);
    }

    @Override
    public Passport create(Passport passportEntity) {
        log.info("creating passport: {}", passportEntity);

        passportEntity.setId(null);

        Passport passport = passportRepository.save(passportEntity);

        log.info("created passport: {}", passport);
        return passport;
    }
}
