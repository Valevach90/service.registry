package com.andersen.banking.service.payment.meeting_impl.exceptions;

/**
 * Custom exception for usage inside Payment service.
 */

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
