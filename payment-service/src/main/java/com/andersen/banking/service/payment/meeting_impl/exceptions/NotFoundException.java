package com.andersen.banking.service.payment.meeting_impl.exceptions;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

  public <T> NotFoundException(Class<T> entityType, Long id) {
    super(String.format("Not found %s with id: %d ", entityType.getSimpleName(), id));
  }

  public <T> NotFoundException(Class<T> entityType) {
    super(String.format("Not found %s", entityType.getSimpleName()));
  }
}
