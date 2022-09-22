package com.andersen.banking.meeting_impl.handler;

import com.andersen.banking.meeting_impl.exception.CreditProductAlreadyExistException;
import com.andersen.banking.meeting_impl.exception.CreditProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CreditServiceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CreditProductNotFoundException.class)
    public String handleNotFoundException(CreditProductNotFoundException exception) {
        log.trace("Caught CreditServiceException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CreditProductAlreadyExistException.class)
    public String handleAlreadyExistException(CreditProductAlreadyExistException exception) {
        log.trace("Caught CreditServiceException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }
}
