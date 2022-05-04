package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.PassportController;
import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.PassportMapper;
import com.andersen.banking.service.registry.meeting_impl.service.AddressService;
import com.andersen.banking.service.registry.meeting_impl.service.PassportService;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Passport controller implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PassportControllerImpl implements PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;
    private final UserService userService;
    private final AddressService addressService;


    @Override
    public PassportDto findById(Long id) {
        log.debug("Find passport by id: {}", id);

        Optional<Passport> passport = passportService.findById(id);
        PassportDto passportDto = passportMapper.toPassportDto(passport.orElseThrow(
                () -> new NotFoundException(NotFoundException.BY_ID)));

        log.debug("Found passport: {} by id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public PassportDto findByUserId(Long id) {
        log.debug("Find passport by user id: {}", id);

        Optional<Passport> passport = passportService.findByUserId(id);
        PassportDto passportDto = passportMapper.toPassportDto(passport.orElseThrow(
                () -> new NotFoundException(NotFoundException.BY_ID)));

        log.debug("Found passport: {} by user id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public PassportDto findByAddressId(Long id) {
        log.debug("Find passport by address id: {}", id);

        Optional<Passport> passport = passportService.findByAddressId(id);
        PassportDto passportDto = passportMapper.toPassportDto(passport.orElseThrow(
                () -> new NotFoundException(NotFoundException.BY_ID)));

        log.debug("Found passport: {} by address id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public Page<PassportDto> findAll(Pageable pageable) {
        log.debug("Find all passports for pageable: {}", pageable);

        Page<PassportDto> allPassportsDto = passportService.findAll(pageable)
                .map(passportMapper::toPassportDto);

        log.debug("Found {} passports", allPassportsDto.getContent().size());
        return allPassportsDto;
    }

    @Override
    public void update(PassportDto passportDto) {
        log.debug("updating passport: {}", passportDto);

        Passport passport = passportMapper.toPassport(passportDto);
        passportService.update(passport);

        log.debug("updated passport: {}", passportDto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("deleting passport with id: {}", id);

        Passport passport = passportService.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));
        passportService.deleteById(id);

        log.debug("deleted passport {} with id: {}", passport, id);
    }

    @Override
    @Transactional
    public PassportDto create(PassportDto passportDto) {
        log.debug("creating passport: {}", passportDto);

        Passport passport = passportMapper.toPassport(passportDto);
        passportService.findByAddressId(passportDto.getAddressId())
                .ifPresent(e -> {
                    throw new NotFoundException("exists"); //TODO change exception message
                });
        passportService.findByUserId(passportDto.getUserId())
                .ifPresent(e -> {
                    throw new NotFoundException("exists");
                });

        Address address = addressService.findById(passportDto.getAddressId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));
        User user = userService.findById(passportDto.getUserId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));
        passport.setAddress(address);
        passport.setUser(user);

        Passport savedPassport = passportService.create(passport);
        PassportDto savedPassportDto = passportMapper.toPassportDto(savedPassport);

        log.debug("created Passport: {}", savedPassportDto);
        return savedPassportDto;
    }
}