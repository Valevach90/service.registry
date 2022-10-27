package com.andersen.banking.meeting_impl.exception;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> objectType, String objectParamName, String objectParamValue) {
        super(String.format("Not found %s with %s = %s", objectType, objectParamName, objectParamValue));
    }
}
