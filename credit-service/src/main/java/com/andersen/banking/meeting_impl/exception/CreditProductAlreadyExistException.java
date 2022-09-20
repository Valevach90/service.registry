package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class CreditProductAlreadyExistException extends RuntimeException {

    public CreditProductAlreadyExistException(UUID uuid) {
        super("Could not find credit product with id " + uuid + ".");
    }
}
