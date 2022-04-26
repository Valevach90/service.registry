package com.andersen.banking.service.registry.meeting_impl.mapping;

/**
 * Exception for mapping actions.
 */
public class MapperException extends RuntimeException {

    public MapperException() {
        super();
    }

    /**
     * This constructor need for MapStruct.
     *
     * @param message about mapping problem
     */
    public MapperException(String message) {
        super(message);
    }
}
