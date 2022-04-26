package com.andersen.banking.service.registry.meeting_impl.exceptions;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public static final String BY_ID = "Not found by id";

    public NotFoundException(String message) {
        super(message);
    }
}
