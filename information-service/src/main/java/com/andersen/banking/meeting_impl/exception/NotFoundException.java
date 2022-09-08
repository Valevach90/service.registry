package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(String objectType, String objectParamName, String objectParamValue) {
        super(String.format("Not found %s with %s = %s", objectType, objectParamName, objectParamValue));
    }

    public <T> NotFoundException(Class<T> entityType, String description, UUID id) {
        super(String.format("Not found %s with %s = %s", entityType.getSimpleName(), description, id));
    }
}
