package com.andersen.banking.service.payment.meeting_impl.handler;

import com.andersen.banking.service.payment.meeting_impl.exceptions.PaymentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class PaymentServiceExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(PaymentServiceException.class)
  public String handlePaymentServiceException(PaymentServiceException exception) {
    log.trace("Caught PaymentServiceException: {}", exception.toString());
    return exception.getLocalizedMessage();
  }
}
