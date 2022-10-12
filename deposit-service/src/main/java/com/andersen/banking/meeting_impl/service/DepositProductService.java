package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestCreateDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductRequestDto;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductFilterDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for working with Deposit Products.
 */

public interface DepositProductService {

    /**
     * Create new deposit product.
     *
     * @param depositProductRequestDto deposit product to create
     * @return deposit product
     */
    DepositProduct create(DepositProductRequestCreateDto depositProductRequestDto);

    /**
     * Find deposit product by id.
     *
     * @param id id of deposit product
     * @return deposit product
     */
    Optional<DepositProduct> findById(UUID id);

    /**
     * Find all deposit products.
     *
     * @param pageable page object
     * @return page of deposit products
     */
    Page<DepositProduct> findAll(Pageable pageable);

    /**
     * Update deposit product.
     *
     * @param product deposit product to update
     */
    void update(DepositProductRequestDto product);

    /**
     * Delete deposit product by id.
     *
     * @param id id of deposit product to delete
     */
    void deleteById(UUID id);

    /**
     * Get page of deposit products filtered by their name and currency
     * <p>
     * If one of the parameters is absent, returns page searched with the specified parameter
     * <p>
     * If both parameters are absent, returns all deposit products
     *
     * @param pageable     page object
     * @param depositName  name of a deposit product
     * @param currencyName name of currency
     */
    Page<DepositProduct> searchByDepositNameAndCurrency(Pageable pageable,
            String depositName,
            String currencyName);

    /**
     * Get deposit products filter with actual parameters values ranges
     */
    DepositProductFilterDto getDepositProductAvailableSetting();

    /**
     * Get filtered deposit products
     *
     * @param depositProductFilterDto deposit product filter
     * @param pageable                page object
     */
    Page<DepositProduct> getFilteredDepositProduct(DepositProductFilterDto depositProductFilterDto,
            Pageable pageable);
}
