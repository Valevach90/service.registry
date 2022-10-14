package com.andersen.banking.meeting_api.dto;

import lombok.Getter;

@Getter
public enum StatusTransfer {
    ACTIVE(0),
    MARKED_ROLLBACK(1),
    PREPARED(2),
    COMMITTED(3),
    ROLLEDBACK(4),
    UNKNOWN(5),
    NO_TRANSACTION(6),
    PREPARING(7),
    COMMITTING(8),
    ROLLING_BACK(9);

    private final int status;

    StatusTransfer(Integer status) {
        this.status = status;
    }

    public static StatusTransfer getStatusTransfer(int value) {
        for(StatusTransfer e: StatusTransfer.values()) {
            if(e.status == value) {
                return e;
            }
        }
        return null;
    }
}
