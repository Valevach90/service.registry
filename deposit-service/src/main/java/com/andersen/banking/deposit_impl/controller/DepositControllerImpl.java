package com.andersen.banking.deposit_impl.controller;

import com.andersen.banking.deposit_api.controller.DepositController;
import com.andersen.banking.deposit_api.dto.DepositDto;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.mapping.DepositMapper;
import com.andersen.banking.deposit_impl.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public void update(DepositDto depositDto) {
        log.debug("Updating deposit: {}", depositDto);

        Deposit deposit = depositMapper.toDeposit(depositDto);
        depositService.update(deposit);

        log.debug("Updated deposit: {}", depositDto);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting deposit with id: {}", id);

        depositService.deleteById(id);

        log.debug("Deleted deposit with id: {}", id);
    }
}
