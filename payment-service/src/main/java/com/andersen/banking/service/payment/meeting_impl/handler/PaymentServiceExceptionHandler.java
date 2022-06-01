package com.andersen.banking.service.payment.meeting_impl.handler;

import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.exception.PaymentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler class for Payment service
 */

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class PaymentServiceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException exception) {
        log.trace("Caught PaymentServiceException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PaymentServiceException.class)
    public String handlePaymentServiceException(PaymentServiceException exception) {
        log.trace("Caught PaymentServiceException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }
}

