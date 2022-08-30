package com.andersen.banking.meeting_impl.exception;

/**
 * Custom exception for cities search.
 */

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
