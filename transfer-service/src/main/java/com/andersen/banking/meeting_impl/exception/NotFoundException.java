package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> entityType, UUID id) {
        super(String.format("Not found %s with id = %s", entityType.getSimpleName(), id.toString()));
    }
}
