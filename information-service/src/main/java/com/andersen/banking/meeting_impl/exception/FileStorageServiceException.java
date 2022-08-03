package com.andersen.banking.meeting_impl.exception;

/**
 * Custom exception for file storage service.
 */

public class FileStorageServiceException extends RuntimeException {

    public FileStorageServiceException(String message) {
        super(message);
    }
}
