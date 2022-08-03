package com.andersen.banking.meeting_impl.exception;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> entityType, Long id) {
        super(String.format("Not found %s with %d id", entityType.getSimpleName(), id));
    }
}
