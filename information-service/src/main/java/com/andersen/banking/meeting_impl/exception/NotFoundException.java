package com.andersen.banking.meeting_impl.exception;

import java.util.UUID;

/**
 * Not Found Exception.
 */
public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(String objectType, String objectParamName, String objectParamValue) {
        super(String.format("Not found %s with %s = %s", objectType, objectParamName, objectParamValue));
    }
}
