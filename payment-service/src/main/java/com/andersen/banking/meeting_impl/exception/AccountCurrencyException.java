package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class AccountCurrencyException extends RuntimeException {

    public AccountCurrencyException(UUID transferId) {
        super(
                String.format(
                        "Can't transfer money between accounts with different currencies for transfer %s",
                        transferId));
    }
}
