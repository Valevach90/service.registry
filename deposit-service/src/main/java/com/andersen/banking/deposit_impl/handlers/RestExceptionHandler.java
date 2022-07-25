package com.andersen.banking.deposit_impl.handlers;

import com.andersen.banking.deposit_api.error.NotFoundError;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.mapping.ErrorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    private final ErrorMapper errorMapper;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected NotFoundError handleNotFoundException(NotFoundException exception) {
        log.error("Caught not found exception: {}", exception.toString());

        NotFoundError notFoundError = errorMapper.toNotFoundError(exception);

        log.error("Handled not found error, message: {}, error code: {}",
                notFoundError.getMessage(),
                notFoundError.getErrorCode());

        return notFoundError;
    }
}
