package com.andersen.banking.deposit_impl.service;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.entities.Transfer;
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

    /**
     * Make transfer
     *
     * @param transfer transfer to make
     */
    void makeTransfer(Transfer transfer);
}
