package com.andersen.banking.deposit_api.dto.messages;

import lombok.*;

@Getter
@Setter
public class AccruedAmount {

    private Long userId;

    private Long amount;

    private Long interestRate;

    private Long accrued;

    private String currency;

    @Override
    public String toString() {
        return "AccruedAmount{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", accrued=" + accrued +
                ", currency='" + currency + '\'' +
                '}';
    }
}
