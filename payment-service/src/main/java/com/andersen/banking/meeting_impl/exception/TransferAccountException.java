package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class TransferAccountException extends RuntimeException {

    public TransferAccountException(UUID transferId) {
        super(
                String.format(
                        "Can't transfer money between cards. The problem with transfer :  %s. Description: ",
                        transferId));
    }
}
