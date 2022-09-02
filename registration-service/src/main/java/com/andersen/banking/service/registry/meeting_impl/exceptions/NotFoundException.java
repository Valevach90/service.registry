package com.andersen.banking.service.registry.meeting_impl.exceptions;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public <T, S> NotFoundException(Class<T> exceptionType, S id) {
        super(String.format("Not found %s with %s id", exceptionType.getSimpleName(), id));
    }
}
