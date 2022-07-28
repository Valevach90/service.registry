package com.andersen.banking.service.registry.meeting_impl.handlers;

import com.andersen.banking.service.registry.meeting_api.error.NotFoundError;
import com.andersen.banking.service.registry.meeting_impl.exceptions.FoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotificationException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;
import com.andersen.banking.service.registry.meeting_impl.mapping.ErrorMapper;
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
  @ExceptionHandler(NotificationException.class)
  public String handleNotificationException(NotificationException exception) {
    log.error("Caught NotificationException: {}", exception.toString());
    return exception.getLocalizedMessage();
  }
}
