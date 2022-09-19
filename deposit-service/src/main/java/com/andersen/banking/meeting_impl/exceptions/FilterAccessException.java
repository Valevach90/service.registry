package com.andersen.banking.meeting_impl.exceptions;

/**
 * Filter Access Exception.
 */
public class FilterAccessException extends RuntimeException {

    public <T> FilterAccessException(Class<T> exceptionType) {
        super(String.format("Filter access exception %s", exceptionType.getSimpleName()));
    }
}
