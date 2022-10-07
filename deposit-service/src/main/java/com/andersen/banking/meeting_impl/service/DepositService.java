package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Optional<Deposit> findById(UUID id);

    /**
     * Find deposits by user id.
     *
     * @param userId   - user id
     * @param pageable - page object
     * @return
     */
    Page<Deposit> findDepositByUserId(UUID userId, Pageable pageable);

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
    void deleteById(UUID id);

    /**
     * Make transfer
     *
     * @param message request transfer message to make transfer
     */
    ResponseTransferMessage makeTransfer(RequestTransferMessage message);

    /**
     * Accrual of deposit interest
     *
     * @param depositId id of deposit to interest accrual
     * @param startedPeriodAmount amount of deposit at start of accrual period
     * @return deposit after the interest accrual
     */
    Deposit accrualDepositInterest(UUID depositId, Long startedPeriodAmount);
}
