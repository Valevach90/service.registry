package com.andersen.banking.service.payment.meeting_impl.exception;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(Class<T> entityType, Long id) {
        super(String.format("Not found %s with %d id", entityType.getSimpleName(), id));
    }
}

