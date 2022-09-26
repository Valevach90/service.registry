package com.andersen.banking.meeting_db.entities;

public enum StatusDescription {
    DEPOSIT("Transfer between deposits is successful"),
    REPLENISHMENT("Replenishment deposit is successful"),
    WITHDRAWAL("Withdrawal deposit is successful"),
    FAILED("Transfer failed"),
    EXIST("The transfer is exist"),
    ROLLED_BACK("Rolled back transactional");

    private final String description;

    StatusDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
