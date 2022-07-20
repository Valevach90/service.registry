package com.andersen.banking.deposit.service.deposit_impl.service;
import com.andersen.banking.deposit.service.deposit_db.entities.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

/**
 * Service for working with Transfers.
 */

public interface TransferService {

    /**
     * Create new transfer.
     *
     * @param transfer transfer to create
     * @return transfer
     */
    Transfer create(Transfer transfer);

    /**
     * Find transfer by id.
     *
     * @param id id of transfer
     * @return transfer
     */
    Optional<Transfer> findById(Long id);

    /**
     * Find all transfers.
     *
     * @param pageable page object
     * @return page of transfers
     */
    Page<Transfer> findAll(Pageable pageable);

    /**
     * Update transfer.
     *
     * @param transfer transfer to update
     */
    void update(Transfer transfer);

    /**
     * Delete transfer by id.
     *
     * @param id id of transfer to delete
     */
    void deleteById(Long id);
}
