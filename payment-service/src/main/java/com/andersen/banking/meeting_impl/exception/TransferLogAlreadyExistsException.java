package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class TransferLogAlreadyExistsException extends Exception {

    public TransferLogAlreadyExistsException(UUID transferId) {
        super(
                String.format(
                        "Can't create new transfer log. TransferLog with id : %s is already exists.",
                        transferId));
    }
}
