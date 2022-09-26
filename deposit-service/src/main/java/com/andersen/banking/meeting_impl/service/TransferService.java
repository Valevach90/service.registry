package com.andersen.banking.meeting_impl.service;
import com.andersen.banking.meeting_db.entities.Transfer;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

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
    Transfer findById(UUID id);

    /**
     * Check transfer is exist.
     *
     * @param id id of transfer
     * @return boolean
     */
    boolean isExist(UUID id);

    /**
     * Check transfer is exist status.
     *
     * @param id id of transfer
     * @param status status transaction
     * @return boolean
     */
    boolean isExistStatus(UUID id, int status);

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
     * Change transfer log status
     *
     * @param id
     * @param status
     */
    void changeTransferStatus(UUID id, int status);

    /**
     * Delete transfer by id.
     *
     * @param id id of transfer to delete
     */
    void deleteById(UUID id);
}
