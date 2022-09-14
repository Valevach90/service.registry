package com.andersen.banking;

import java.util.UUID;

public class CreditProductNotFoundException extends RuntimeException {

    public CreditProductNotFoundException(UUID uuid) {
        super("Could not find credit product with id " + uuid + ".");
    }
}
