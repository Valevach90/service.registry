package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.DepositProductController;
import com.andersen.banking.meeting_api.dto.DepositProductDto;
import com.andersen.banking.meeting_api.dto.DepositProductFilterDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.mapping.DepositProductMapper;
import com.andersen.banking.meeting_impl.service.DepositProductService;
import java.util.UUID;
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
    public DepositProductDto findById(UUID id) {
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
    public void deleteById(UUID id) {
        log.debug("Deleting deposit product with id: {}", id);

        depositProductService.deleteById(id);

        log.debug("Deleted deposit product with id: {}", id);
    }

    @Override
    public Page<DepositProductDto> findByDepositNameAndCurrency(Pageable pageable, String depositName, String currency) {
        log.debug("Searching deposit products with name = {}, with currency = {} for pageable = {}",
                depositName, currency, pageable);

        Page<DepositProductDto> foundProducts = depositProductService
                .searchByDepositNameAndCurrency(pageable, depositName, currency)
                .map(depositProductMapper::toDepositProductDto);

        log.debug("Search was successful. Found {} deposit products", foundProducts.getContent().size());
        return foundProducts;
    }

    @Override
    public DepositProductFilterDto getDepositProductAvailableSetting() {
        log.debug("Trying to getting deposit product available setting");

        DepositProductFilterDto depositProductFilterDto = depositProductService.getDepositProductAvailableSetting();

        log.debug("Getting deposit product available setting: {}", depositProductFilterDto);
        return depositProductFilterDto;
    }

    @Override
    public Page<DepositProductDto> getFilteredDepositProducts(Pageable pageable, DepositProductFilterDto depositProductFilterDto) {
        log.info("Trying to get filtered deposit products using filter: {}", depositProductFilterDto);

        Page<DepositProductDto> foundProducts = depositProductService
                .getFilteredDepositProduct(depositProductFilterDto, pageable)
                .map(depositProductMapper::toDepositProductDto);

        log.info("Getting filtered deposit products: {}", foundProducts);
        return foundProducts;
    }
}