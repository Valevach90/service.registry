package com.andersen.banking.deposit.service.deposit_impl.service;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
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
    Optional<DepositProduct> findById(Long id);

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
    void deleteById(Long id);
}
