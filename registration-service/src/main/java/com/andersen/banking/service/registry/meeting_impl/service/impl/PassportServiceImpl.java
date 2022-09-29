package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.PassportRepository;
import com.andersen.banking.service.registry.meeting_impl.date.DateSupportService;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_impl.service.PassportService;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private static final Long PASSPORT_VALIDITY = 10L;
    private static final Long PASSPORT_OWNER_MINIMAL_AGE = 18L;

    private final PassportRepository passportRepository;
    private final AddressService addressService;
    private final UserService userService;
    private final DateSupportService dateSupportService;

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
    public Optional<Passport> findByUserId(UUID userId) {
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

        validatePassport(passport);

        Passport foundPassport = passportRepository.findById(passport.getId())
                .orElseThrow(() -> new NotFoundException(Passport.class, passport.getId()));

        passport.setUserId(foundPassport.getUserId());
        passport.setAddress(foundPassport.getAddress());

        passportRepository.save(passport);

        log.info("Updated passport : {}", passport);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleting passport with id: {}", id);

        Passport passport = findById(id).orElseThrow(
                () -> new NotFoundException(Passport.class, id));
        passportRepository.deleteById(id);

        log.info("deleted passport: {} with id: {}", passport, id);
    }

    @Override
    @Transactional
    public Passport create(Passport passport, UUID userId, Long addressId) {
        log.info("creating passport: {}", passport);

        validatePassport(passport);

        findByAddressId(addressId)
                .ifPresent(e -> {
                    throw new FoundException(Address.class, addressId);
                });
        findByUserId(userId)
                .ifPresent(e -> {
                    throw new FoundException(User.class, userId);
                });

        Address address = addressService.findById(addressId)
                .orElseThrow(() -> new NotFoundException(Address.class, addressId));
        User user = userService.findById(userId);
        passport.setAddress(address);
        passport.setUserId(user.getId());

        passport.setId(null);

        Passport savedPassport = passportRepository.save(passport);

        log.info("created passport: {}", savedPassport);
        return savedPassport;
    }

    private void validatePassport(final Passport passport) {
        final LocalDate dateIssue = passport.getDateIssue();
        final LocalDate terminationDate = passport.getTerminationDate();
        final LocalDate birthDate = passport.getBirthday();

        if (dateSupportService.checkIfDateIsLaterThanToday(dateIssue)) {
            throw new ValidationException(
                    String.format("Expiration date is incorrect: %s", dateIssue));
        }

        if (!dateSupportService.checkIfDateIsLaterThanToday(terminationDate)) {
            throw new ValidationException(
                    String.format("Expiration date is incorrect: %s", terminationDate));
        }
        if (!(dateSupportService.checkIfTwoDatesAreEqual(dateIssue.plusYears(PASSPORT_VALIDITY),
                terminationDate))) {
            throw new ValidationException(
                    String.format(
                            "Registration date and expiration date must have exactly %d years difference",
                            PASSPORT_VALIDITY));
        }
        if (dateSupportService.checkIfDateIsLaterThanToday(
                birthDate.plusYears(PASSPORT_OWNER_MINIMAL_AGE))) {
            throw new ValidationException(
                    String.format("Passport owner should be at least %d years old",
                            PASSPORT_OWNER_MINIMAL_AGE));
        }
    }
}
