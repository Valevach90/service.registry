package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_impl.service.PassportService;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
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
    private final AddressService addressService;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Passport> findById(Long id) {
        log.info("Find passport by id: {}", id);

        Optional<Passport> passport = passportRepository.findById(id);

        log.info("Found passport: {} by id: {}", passport, id);
        return passport;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Passport> findByUserId(Long userId) {
        log.info("Find passport by id: {}", userId);

        Optional<Passport> passport = passportRepository.findByUserId(userId);

        log.info("Found passport: {} by user id: {}", passport, userId);
        return passport;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Passport> findByAddressId(Long addressId) {
        log.info("Find passport by address id: {}", addressId);

        Optional<Passport> passport = passportRepository.findByAddressId(addressId);

        log.info("Found passport: {} by address id: {}", passport, addressId);
        return passport;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Passport> findAll(Pageable pageable) {
        log.info("Find all passports for pageable: {}", pageable);

        Page<Passport> allPassports = passportRepository.findAll(pageable);

        log.info("Found {} passports", allPassports.getContent().size());
        return allPassports;
    }

    @Override
    @Transactional
    public void update(Passport passport) {
        log.info("Try to update passport: {}", passport);

        Passport foundPassport = passportRepository.findById(passport.getId())
                .orElseThrow(() -> new NotFoundException(Passport.class, passport.getId()));

        passport.setUser(foundPassport.getUser());
        passport.setAddress(foundPassport.getAddress());

        passportRepository.save(passport);

        log.info("Updated passport : {}", passport);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleting passport with id: {}", id);

        Passport passport = findById(id).orElseThrow(() -> new NotFoundException(Passport.class, id));
        passportRepository.deleteById(id);

        log.info("deleted passport: {} with id: {}", passport, id);
    }

    @Override
    @Transactional
    public Passport create(Passport passport, Long userId, Long addressId) {
        log.info("creating passport: {}", passport);

        findByAddressId(userId)
                .ifPresent(e -> {
                    throw new FoundException(User.class, userId);
                });
        findByUserId(addressId)
                .ifPresent(e -> {
                    throw new FoundException(Address.class, addressId);
                });

        Address address = addressService.findById(addressId)
                .orElseThrow(() -> new NotFoundException(Address.class, addressId));
        User user = userService.findById(userId)
                .orElseThrow(() -> new NotFoundException(User.class, userId));
        passport.setAddress(address);
        passport.setUser(user);

        passport.setId(null);

        Passport savedPassport = passportRepository.save(passport);

        log.info("created passport: {}", savedPassport);
        return savedPassport;
    }
}
