package com.andersen.banking.service.payment.meeting_impl.exceptions;

/**
 * Custom exception for usage inside Payment service.
 */

public class PaymentServiceException extends RuntimeException {

  public PaymentServiceException(String message) {
    super(message);
  }
}
