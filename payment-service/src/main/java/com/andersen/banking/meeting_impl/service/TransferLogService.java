package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.TransferLog;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;

import java.util.UUID;

public interface TransferLogService {

    /**
     * This method registers new transferLog.
     *
     * @param
     * @return TransferLog
     */
    TransferLog create(TransferLog transferLog);

    /**
     * Return transferLog by id.
     *
     * @param id of the transferLog
     * @return transferLog
     */
    TransferLog findById(UUID id);

    /**
     * Assume transferLog's status in database.
     *
     * @param id of the transferLog
     * @param status transaction
     * @return boolean
     */
    boolean isExistStatus(UUID id, int status);

    /**
     * Assume transferLog in database.
     *
     * @param id of the transferLog
     * @return boolean
     */
    boolean isExist(UUID id);

    /**
     * Update transferLog.
     *
     * @param transferLog update transferLog
     * @return TransferLog
     */
    TransferLog update(TransferLog transferLog);

    /**
     * Create transferLog according to request kafka message and status.
     *
     * @param requestTransferMessage message
     * @throws com.andersen.banking.meeting_impl.exception.TransferLogAlreadyExistsException
     * @return TransferLog
     */
    TransferLog createTransferLogAccordingToRequestKafkaMessage(
            RequestTransferMessage requestTransferMessage);

    void changeTransferLogStatus(UUID id, int status);
}
