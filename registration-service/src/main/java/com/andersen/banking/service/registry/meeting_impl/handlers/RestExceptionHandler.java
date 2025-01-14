package com.andersen.banking.service.registry.meeting_impl.handlers;

import com.andersen.banking.service.registry.meeting_api.error.NotFoundError;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.WrongNameException;
import com.andersen.banking.service.registry.meeting_impl.mapping.ErrorMapper;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoundException.class)
    protected NotFoundError handleFoundException(FoundException exception) {
        log.error("Caught found exception: {}", exception.toString());

        NotFoundError notFoundError = errorMapper.toFoundError(exception);

        log.error("Handled found error, message: {}, error code: {}",
                notFoundError.getMessage(),
                notFoundError.getErrorCode());

        return errorMapper.toFoundError(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public String handlePaymentServiceException(ValidationException exception) {
        log.error("Caught ValidationException: {}", exception.toString());
        return exception.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(WrongNameException.class)
    public String handleWrongNameException(WrongNameException exception) {
        log.error("incorrect data: first name, last name or patronymic: {}", exception.toString());
        return exception.getLocalizedMessage();
    }

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
