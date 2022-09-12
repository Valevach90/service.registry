package com.andersen.banking.deposit_impl.service;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

/**
 * Service for working with Deposit Products.
 */

public interface DepositProductService {

    /**
     * Create new deposit product.
     *
     * @param product deposit product to create
     * @return deposit product
     */
    DepositProduct create(DepositProduct product);

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
    void update(DepositProduct product);

    /**
     * Delete deposit product by id.
     *
     * @param id id of deposit product to delete
     */
    void deleteById(UUID id);

    /**
     * Get page of deposit products
     * filtered by their name and currency
     *
     * If one of the parameters is absent, returns
     * page searched with the specified parameter
     *
     * If both parameters are absent, returns all
     * deposit products
     *
     * @param pageable page object
     * @param depositName name of a deposit product
     * @param currencyName name of currency
     * */
    Page<DepositProduct> searchByDepositNameAndCurrency(Pageable pageable,
                                                        String depositName,
                                                        String currencyName);
}
