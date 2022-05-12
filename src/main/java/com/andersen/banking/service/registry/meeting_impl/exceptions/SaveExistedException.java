package com.andersen.banking.service.registry.meeting_impl.exceptions;

public class SaveExistedException extends RuntimeException {

    public <T> SaveExistedException(Class<T> exceptionType, Long id) {
        super(String.format("Attempt to save existed %s with $d id",  exceptionType.getSimpleName(), id));
    }
}
