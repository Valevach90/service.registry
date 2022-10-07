package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.DepositController;
import com.andersen.banking.meeting_api.dto.DepositCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DepositDto;
import com.andersen.banking.meeting_api.dto.DepositRequestDto;
import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.mapping.DepositMapper;
import com.andersen.banking.meeting_impl.service.DepositService;
import com.andersen.banking.meeting_impl.util.JwtUtil;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

/**
 * Deposit controller implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DepositControllerImpl implements DepositController {

    private final DepositService depositService;

    private final DepositMapper depositMapper;


    @Override
    public DepositDto create(DepositCreateRequestDto depositDto) {
        log.debug("Creating deposit: {}", depositDto);

        Deposit deposit = depositMapper.toDeposit(depositDto);

        Deposit savedDeposit = depositService.create(deposit);

        DepositDto savedDepositDto = depositMapper.toDepositDto(savedDeposit);

        log.debug("Created deposit: {}", savedDepositDto);
        return savedDepositDto;
    }

    @Override
    public DepositDto findById(UUID id) {
        log.debug("Find deposit by id: {}", id);

        Optional<Deposit> deposit = depositService.findById(id);

        DepositDto depositDto = depositMapper.toDepositDto(deposit.orElseThrow(
                () -> new NotFoundException(Deposit.class, id)));

        log.debug("Found deposit by id: {}", depositDto);
        return depositDto;
    }

    @Override
    public Page<DepositRequestDto> findAll(Pageable pageable) {
        log.debug("Find all deposits for pageable: {}", pageable);

        Page<DepositRequestDto> allDepositDto = depositService.findAll(pageable)
                .map(depositMapper::toDepositRequest);

        log.debug("Found {} deposits", allDepositDto.getContent().size());
        return allDepositDto;
    }

    @Override
    public Page<DepositDto> findDepositsByUserId(UUID userId, Pageable pageable) {
        log.debug("Find all deposits for user {} and pageable: {}", userId, pageable);

        Page<DepositDto> depositDtos = depositService.findDepositByUserId(userId, pageable)
                .map(depositMapper::toDepositDto);

        log.debug("Found {} deposits", depositDtos.getContent().size());
        return depositDtos;
    }

    @Override
    public Page<DepositDto> findDepositsByCurrentUserId(
            Authentication authentication,
            Pageable pageable) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String stringId = JwtUtil.extractIdFromToken(jwt);
        log.debug("Find all deposits with authentication for user with id: {} and pageable: {}",
                stringId, pageable);
        return findDepositsByUserId(UUID.fromString(stringId), pageable);
    }

    @Override
    public void update(DepositDto depositDto) {
        log.debug("Updating deposit: {}", depositDto);

        Deposit deposit = depositMapper.toDeposit(depositDto);
        depositService.update(deposit);

        log.debug("Updated deposit: {}", depositDto);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting deposit with id: {}", id);

        depositService.deleteById(id);

        log.debug("Deleted deposit with id: {}", id);
    }
}
