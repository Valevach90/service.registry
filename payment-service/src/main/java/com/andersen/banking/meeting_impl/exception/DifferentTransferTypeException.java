package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

/** Exception for KafkaTransferService */
public class DifferentTransferTypeException extends Exception {

    public DifferentTransferTypeException(UUID transferId) {
        super(
                String.format(
                        "Can't transfer money between different SRC and DEST types for transfer %s",
                        transferId));
    }
}
