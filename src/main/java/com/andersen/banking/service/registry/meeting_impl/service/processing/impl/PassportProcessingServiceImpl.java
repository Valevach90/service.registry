package com.andersen.banking.service.registry.meeting_impl.service.processing.impl;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import com.andersen.banking.service.registry.meeting_impl.mapping.PassportMapper;
import com.andersen.banking.service.registry.meeting_impl.service.local.PassportService;
import com.andersen.banking.service.registry.meeting_impl.service.processing.PassportProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportProcessingServiceImpl implements PassportProcessingService {
    private final PassportService passportService;
    private final PassportMapper passportMapper;

    @Override
    public PassportDto findById(Long id) {
        log.info("Find passport by id: {}", id);

        PassportEntity passportEntity = passportService.findById(id);
        PassportDto passportDto = passportMapper.toPassportDto(passportEntity);

        log.info("Found passport: {} by id: {}", passportDto, id);
        return passportDto;
    }

    @Override
    public PassportDto findByUserId(Long userId) {
        log.info("Find passport by userId id: {}", userId);

        PassportEntity passportEntity = passportService.findByUserId(userId);
        PassportDto passportDto = passportMapper.toPassportDto(passportEntity);

        log.info("Found passport: {} by userId id: {}", passportDto, userId);
        return passportDto;
    }

    @Override
    public PassportDto findByAddressId(Long addressId) {
        log.info("Find passport by address id: {}", addressId);

        PassportEntity passportEntity = passportService.findByAddressId(addressId);
        PassportDto passportDto = passportMapper.toPassportDto(passportEntity);

        log.info("Found passport: {} by address id: {}", passportDto, addressId);
        return passportDto;
    }

    @Override
    public Page<PassportDto> findAll(Pageable pageable) {
        log.info("Find all passports for pageable: {}", pageable);

        Page<PassportDto> allPassportsDto = passportService.findAll(pageable)
                .map(passportMapper::toPassportDto);

        log.info("Found {} passports", allPassportsDto.getContent().size());
        return allPassportsDto;
    }
}
