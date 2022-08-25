package com.andersen.banking.deposit_impl.exceptions;

import java.util.UUID;

/**
 * Not Found Exception.
 */

public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(Class<T> exceptionType, Long id) {
        super(String.format("Not found %s with %d id", exceptionType.getSimpleName(), id));
    }

    public <T> NotFoundException(Class<T> exceptionType, String number) {
        super(String.format("Not found %s with number %s", exceptionType.getSimpleName(), number));
    }

    public <T> NotFoundException(Class<T> exceptionType, UUID id) {
        super(String.format("Not found %s with number %s", exceptionType.getSimpleName(), id));
    }
}
