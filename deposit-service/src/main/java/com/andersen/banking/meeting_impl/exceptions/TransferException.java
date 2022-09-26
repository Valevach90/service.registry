package com.andersen.banking.meeting_impl.exceptions;

import java.util.UUID;

public class TransferException extends RuntimeException {

    public TransferException(UUID transferId) {
        super(
                String.format(
                        "Can't transfer money between types in transfer. The problem with transfer:  %s.",
                        transferId));
    }
}
