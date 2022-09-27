package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(UUID uuid) {
        super(String.format("Could not find currency with id: %s", uuid));
    }
}
