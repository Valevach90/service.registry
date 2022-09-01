package com.andersen.banking.meeting_impl.exception;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public <T, S> NotFoundException(Class<T> exceptionType, S id) {
        super(String.format("Not found %s with id: %s ", exceptionType.getSimpleName(), id));
    }
}
