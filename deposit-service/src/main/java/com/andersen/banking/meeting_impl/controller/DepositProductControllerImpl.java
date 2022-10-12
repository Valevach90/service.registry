package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.DepositProductController;
import com.andersen.banking.meeting_api.dto.CurrencyDto;
import com.andersen.banking.meeting_api.dto.DepositTypeDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestCreateDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductResponseDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductFilterDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.mapping.CurrencyMapper;
import com.andersen.banking.meeting_impl.mapping.DepositProductMapper;
import com.andersen.banking.meeting_impl.mapping.DepositTypeMapper;
import com.andersen.banking.meeting_impl.service.CurrenciesService;
import com.andersen.banking.meeting_impl.service.DepositProductService;
import com.andersen.banking.meeting_impl.service.DepositTypeService;
import java.util.List;
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
    private final CurrenciesService currenciesService;
    private final DepositTypeService depositTypeService;

    private final DepositProductMapper depositProductMapper;
    private final CurrencyMapper currencyMapper;
    private final DepositTypeMapper depositTypeMapper;

    @Override
    public DepositProductResponseDto create(DepositProductRequestCreateDto depositProductRequestDto) {
        log.debug("Creating deposit product: {}", depositProductRequestDto);

        DepositProduct savedDepositProduct = depositProductService.create(depositProductRequestDto);

        DepositProductResponseDto savedDepositProductResponseDto =
                depositProductMapper.toDepositProductDto(savedDepositProduct);

        log.debug("Created deposit product: {}", savedDepositProductResponseDto);
        return savedDepositProductResponseDto;
    }

    @Override
    public DepositProductResponseDto findById(UUID id) {
        log.debug("Find deposit product by id: {}", id);

        Optional<DepositProduct> depositProduct = depositProductService.findById(id);

        DepositProductResponseDto depositProductResponseDto =
                depositProductMapper.toDepositProductDto(depositProduct.orElseThrow(
                () -> new NotFoundException(DepositProduct.class, id)));

        log.debug("Found deposit product by id: {}", depositProductResponseDto);
        return depositProductResponseDto;
    }

    @Override
    public Page<DepositProductResponseDto> findAll(Pageable pageable) {
        log.debug("Find all deposit products for pageable: {}", pageable);

        Page<DepositProductResponseDto> allDepositProductDto = depositProductService.findAll(pageable)
                .map(depositProductMapper::toDepositProductDto);

        log.debug("Found {} deposit products", allDepositProductDto.getContent().size());
        return allDepositProductDto;
    }

    @Override
    public List<CurrencyDto> findCurrencies() {
        log.debug("Try to get list of currency.");
        return currenciesService.findAll().stream()
                .map(currencyMapper::toCurrencyDto)
                .toList();
    }

    @Override
    public List<DepositTypeDto> findDepositType() {
        log.debug("Try to get list of deposit type.");
        return depositTypeService.findAll().stream()
                .map(depositTypeMapper::toDepositTypeDto)
                .toList();
    }

    @Override
    public void update(DepositProductRequestDto depositProductRequestDto) {
        log.debug("Updating deposit product: {}", depositProductRequestDto);

        depositProductService.update(depositProductRequestDto);

        log.debug("Updated deposit product: {}", depositProductRequestDto);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting deposit product with id: {}", id);

        depositProductService.deleteById(id);

        log.debug("Deleted deposit product with id: {}", id);
    }

    @Override
    public Page<DepositProductResponseDto> findByDepositNameAndCurrency(Pageable pageable, String depositName, String currency) {
        log.debug("Searching deposit products with name = {}, with currency = {} for pageable = {}",
                depositName, currency, pageable);

        Page<DepositProductResponseDto> foundProducts = depositProductService
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
    public Page<DepositProductResponseDto> getFilteredDepositProducts(Pageable pageable, DepositProductFilterDto depositProductFilterDto) {
        log.info("Trying to get filtered deposit products using filter: {}", depositProductFilterDto);

        Page<DepositProductResponseDto> foundProducts = depositProductService
                .getFilteredDepositProduct(depositProductFilterDto, pageable)
                .map(depositProductMapper::toDepositProductDto);

        log.info("Getting filtered deposit products: {}", foundProducts);
        return foundProducts;
    }
}
