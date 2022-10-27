package com.andersen.banking.meeting_impl.exceptions;

import java.util.UUID;

/**
 * Not Found Exception.
 */

public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(Class<T> exceptionType, UUID id) {
        super(String.format("Not found %s with %s id", exceptionType.getSimpleName(), id));
    }

    public <T> NotFoundException(Class<T> exceptionType, String number) {
        super(String.format("Not found %s with number %s", exceptionType.getSimpleName(), number));
    }

    public <T> NotFoundException(Class<T> exceptionType,  String number, UUID uuid) {
        super(String.format("Not found %s cards for transferring to deposit number: %s. User: %s", exceptionType.getSimpleName(), number, uuid));
    }
}
