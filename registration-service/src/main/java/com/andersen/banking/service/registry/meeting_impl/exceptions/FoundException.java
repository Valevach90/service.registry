package com.andersen.banking.service.registry.meeting_impl.exceptions;

/**
 * Not Found Exception.
 */
public class FoundException extends RuntimeException {

    public <T, S> FoundException(Class<T> exceptionType, S id) {
        super(String.format("Found %s with %s id", exceptionType.getSimpleName(), id));
    }
}

