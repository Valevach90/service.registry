package com.andersen.banking.service.registry.meeting_impl.exceptions;

public class SaveExistedException extends RuntimeException {

    public <T, S> SaveExistedException(Class<T> exceptionType, S id) {
        super(String.format("Attempt to save existed %s with %s id",  exceptionType.getSimpleName(), id));
    }
}
