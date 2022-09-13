package com.andersen.banking.deposit_impl.controller;

import com.andersen.banking.deposit_api.controller.DepositController;
import com.andersen.banking.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.mapping.DepositMapper;
import com.andersen.banking.deposit_impl.service.DepositService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public DepositDto create(DepositDto depositDto) {
        log.debug("Creating deposit: {}", depositDto);

        Deposit deposit = depositMapper.toDeposit(depositDto);

        Deposit savedDeposit = depositService.create(deposit);

        DepositDto savedDepositDto = depositMapper.toDepositDto(savedDeposit);

        log.debug("Created deposit: {}", savedDepositDto);
        return savedDepositDto;
    }

    @Override
    public DepositDto findById(Long id) {
        log.debug("Find deposit by id: {}", id);

        Optional<Deposit> deposit = depositService.findById(id);

        DepositDto depositDto = depositMapper.toDepositDto(deposit.orElseThrow(
                () -> new NotFoundException(Deposit.class, id)));

        log.debug("Found deposit by id: {}", depositDto);
        return depositDto;
    }

    @Override
    public Page<DepositDto> findAll(Pageable pageable) {
        log.debug("Find all deposits for pageable: {}", pageable);

        Page<DepositDto> allDepositDto = depositService.findAll(pageable)
                .map(depositMapper::toDepositDto);

        log.debug("Found {} deposits", allDepositDto.getContent().size());
        return allDepositDto;
    }

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
        //todo
        //this not work
        //change to findDepositsByUserId(UUID.fromString(stringId), pageable) when change userid to UUID
        return findDepositsByUserId(Long.valueOf(stringId), pageable);
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
