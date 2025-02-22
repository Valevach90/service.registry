package com.andersen.banking.meeting_db.entities;

public enum StatusDescription {
    BETWEEN_CARDS("Successfully transfer money between cards"),
    GET_FROM_CARD("Get money from card is successfully"),

    GET_FROM_DEPOSIT("Get money from deposit successfully"),

    FAILED("Transfer failed"),

    EXIST("The transfer is exist");

    private final String description;

    StatusDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
