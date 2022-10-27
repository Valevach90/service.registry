package com.andersen.banking.meeting_impl.feign.dto;

import lombok.Getter;

@Getter
public enum StatusTransfer {
    ACTIVE(0), PREPARING(7), ROLLEDBACK(4);

    private final int id;

    StatusTransfer(Integer id) {
        this.id = id;
    }

}
