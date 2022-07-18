package com.andersen.banking.deposit.service.deposit_impl.service;
import com.andersen.banking.deposit.service.deposit_db.entities.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

/**
 * Service for working with Deposits.
 */

public interface DepositService {

    /**
     * Create new deposit.
     *
     * @param deposit deposit to create
     * @return deposit
     */
    Deposit create(Deposit deposit);

    /**
     * Find deposit by id.
     *
     * @param id id of deposit
     * @return deposit
     */
    Optional<Deposit> findById(Long id);

    /**
     * Find all deposits.
     *
     * @param pageable page object
     * @return page of deposits
     */
    Page<Deposit> findAll(Pageable pageable);

    /**
     * Update deposit.
     *
     * @param deposit deposit to update
     */
    void update(Deposit deposit);

    /**
     * Delete deposit by id.
     *
     * @param id id of deposit to delete
     */
    void deleteById(Long id);
}
