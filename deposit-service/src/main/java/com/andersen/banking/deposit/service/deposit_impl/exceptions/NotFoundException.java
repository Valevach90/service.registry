package com.andersen.banking.deposit.service.deposit_impl.exceptions;

/**
 * Not Found Exception.
 */

public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(Class<T> exceptionType, Long id) {
        super(String.format("Not found %s with %d id", exceptionType.getSimpleName(), id));
    }
}
