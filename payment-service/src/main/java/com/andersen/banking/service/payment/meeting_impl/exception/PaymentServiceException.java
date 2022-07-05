package com.andersen.banking.service.payment.meeting_impl.exception;

/**
 * Custom exception for usage inside Payment service.
 */

public class PaymentServiceException extends RuntimeException {

    public PaymentServiceException(String message) {
        super(message);
    }
}

