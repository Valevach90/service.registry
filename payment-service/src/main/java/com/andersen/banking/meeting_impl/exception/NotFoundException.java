package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {


  public <T> NotFoundException(Class<T> entityType) {
    super(String.format("Not found %s", entityType.getSimpleName()));
  }

  public <T> NotFoundException(Class<T> entityType, UUID id) {
    super(String.format("Not found %s with id = %s", entityType.getSimpleName(), id));
  }

}
