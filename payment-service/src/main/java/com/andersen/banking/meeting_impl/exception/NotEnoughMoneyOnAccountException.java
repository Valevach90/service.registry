package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class NotEnoughMoneyOnAccountException extends RuntimeException {

    public NotEnoughMoneyOnAccountException(UUID accountId) {
        super(
                String.format(
                        "Can't transfer money because account with id : {} have not enough money. %s",
                        accountId));
    }
}
