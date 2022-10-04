package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class CreditProductNotFoundException extends RuntimeException {

    public CreditProductNotFoundException(UUID uuid) {
        super(String.format("Could not find credit product with id: %s", uuid));
    }
}
