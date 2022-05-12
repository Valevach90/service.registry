package com.andersen.banking.service.registry.meeting_impl.exceptions;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(Class<T> exceptionType, Long id) {
        super(String.format("Not found %s with %d id", exceptionType.getSimpleName(), id));
    }
}