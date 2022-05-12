package com.andersen.banking.service.registry.meeting_impl.exceptions;

/**
 * Not Found Exception.
 */
public class FoundException extends RuntimeException {

    public <T> FoundException(Class<T> exceptionType, Long id) {
        super(String.format("Found %s with %d id", exceptionType.getSimpleName(), id));
    }
}

