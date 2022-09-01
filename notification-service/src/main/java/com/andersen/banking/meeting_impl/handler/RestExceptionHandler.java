package com.andersen.banking.meeting_impl.handler;

import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception Handler Controller.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        log.error("Caught MethodArgumentNotValidException: {}", exception.toString());
        FieldError fieldError = exception.getFieldError();
        if (fieldError != null) {
            return fieldError.getDefaultMessage();
        }
        return exception.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("Caught IllegalArgumentException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public String handleSQLException(SQLException exception) {
        log.error("Caught SQLException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }
}
