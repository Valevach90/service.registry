package com.andersen.banking.service.registry.meeting_impl.handlers;

import com.andersen.banking.service.registry.meeting_api.error.NotFoundError;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.ErrorMapper;
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
        log.trace("Caught not found exception: {}", exception.toString());

        NotFoundError notFoundError = errorMapper.toNotFoundError(exception);

        log.trace("Handled not found error, message: {}, error code: {}",
                notFoundError.getMessage(),
                notFoundError.getErrorCode());

        return notFoundError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoundException.class)
    protected NotFoundError handleFoundException(FoundException exception) {
        log.trace("Caught found exception: {}", exception.toString());

        NotFoundError notFoundError = errorMapper.toFoundError(exception);

        log.trace("Handled found error, message: {}, error code: {}",
                notFoundError.getMessage(),
                notFoundError.getErrorCode());

        return errorMapper.toFoundError(exception);
    }
}