package com.andersen.banking.deposit.service.deposit_impl.controller;

import com.andersen.banking.deposit.service.deposit_api.controller.DepositProductController;
import com.andersen.banking.deposit.service.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit.service.deposit_impl.mapping.DepositProductMapper;
import com.andersen.banking.deposit.service.deposit_impl.service.DepositProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Deposit Product controller implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DepositProductControllerImpl implements DepositProductController {

    private final DepositProductService depositProductService;

    private final DepositProductMapper depositProductMapper;

    @Override
    public DepositProductDto create(DepositProductDto depositProductDto) {
        log.debug("Creating deposit product: {}", depositProductDto);

        DepositProduct depositProduct = depositProductMapper.toDepositProduct(depositProductDto);

        DepositProduct savedDepositProduct = depositProductService.create(depositProduct);

        DepositProductDto savedDepositProductDto = depositProductMapper.toDepositProductDto(savedDepositProduct);

        log.debug("Created deposit product: {}", savedDepositProductDto);
        return savedDepositProductDto;
    }

    @Override
    public DepositProductDto findById(Long id) {
        log.debug("Find deposit product by id: {}", id);

        Optional<DepositProduct> depositProduct = depositProductService.findById(id);

        DepositProductDto depositProductDto = depositProductMapper.toDepositProductDto(depositProduct.orElseThrow(
                () -> new NotFoundException(DepositProduct.class, id)));

        log.debug("Found deposit product by id: {}", depositProductDto);
        return depositProductDto;
    }

    @Override
    public Page<DepositProductDto> findAll(Pageable pageable) {
        log.debug("Find all deposit products for pageable: {}", pageable);

        Page<DepositProductDto> allDepositProductDto = depositProductService.findAll(pageable)
                .map(depositProductMapper::toDepositProductDto);

        log.debug("Found {} deposit products", allDepositProductDto.getContent().size());
        return allDepositProductDto;
    }

    @Override
    public void update(DepositProductDto depositProductDto) {
        log.debug("Updating deposit product: {}", depositProductDto);

        DepositProduct depositProduct = depositProductMapper.toDepositProduct(depositProductDto);
        depositProductService.update(depositProduct);

        log.debug("Updated deposit product: {}", depositProductDto);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting deposit product with id: {}", id);

        depositProductService.deleteById(id);

        log.debug("Deleted deposit product with id: {}", id);
    }
}
