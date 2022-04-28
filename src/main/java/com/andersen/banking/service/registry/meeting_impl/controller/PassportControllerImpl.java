package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.PassportController;
import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_impl.service.processing.PassportProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Passport controller implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PassportControllerImpl implements PassportController {

    private final PassportProcessingService passportProcessingService;

    @Override
    public PassportDto findById(Long id) {
        log.trace("Find passport by id: {}", id);

        PassportDto passportDto = passportProcessingService.findById(id);

        log.trace("Found passport: {} by id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public PassportDto findByUserId(Long id) {
        log.trace("Find passport by user id: {}", id);

        PassportDto passportDto = passportProcessingService.findByUserId(id);

        log.trace("Found passport: {} by user id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public PassportDto findByAddressId(Long id) {
        log.trace("Find passport by address id: {}", id);

        PassportDto passportDto = passportProcessingService.findByAddressId(id);

        log.trace("Found passport: {} by address id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public Page<PassportDto> findAll(Pageable pageable) {
        log.trace("Find all passports for pageable: {}", pageable);

        Page<PassportDto> allPassportsDto = passportProcessingService.findAll(pageable);

        log.trace("Found {} passports", allPassportsDto.getContent().size());
        return allPassportsDto;
    }
}
