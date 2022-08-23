package com.andersen.banking.meeting_impl.exception;

public class RequestValidationException extends RuntimeException {
    public <T> RequestValidationException(Class<T> entityType) {
        super(String.format("Can't execute transfer %s, because it failed verification for payment and currency fields", entityType.getSimpleName()));
    }
}