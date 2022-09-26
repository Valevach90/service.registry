package com.andersen.banking.meeting_impl.exceptions;

import java.util.UUID;

public class TransferAlreadyExistsException extends RuntimeException {

    public TransferAlreadyExistsException(UUID transferId) {
        super(
                String.format(
                        "Can't create new transfer log. TransferLog with id : %s is already exists.",
                        transferId));
    }
}
