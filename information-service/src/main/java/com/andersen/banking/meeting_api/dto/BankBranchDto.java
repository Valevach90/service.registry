package com.andersen.banking.meeting_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBranchDto {

    @NotNull
    private Long id;

    @NotBlank
    private String streetName;

    @NotBlank
    private String house;

    @NotBlank
    private String building;

    @NotBlank
    private String branchNumber;

    @NotBlank
    private String branchCoordinates;

    @NotBlank
    private boolean workAtWeekend;

    @NotBlank
    private boolean cashWithdraw;

    @NotBlank
    private boolean moneyTransfer;

    @NotBlank
    private boolean acceptPayment;

    @NotBlank
    private boolean currencyExchange;

    @NotBlank
    private boolean exoticCurrency;

    @NotBlank
    private boolean ramp;

    @NotBlank
    private boolean replenishCard;

    @NotBlank
    private boolean replenishAccount;

    @NotBlank
    private boolean consultation;

    @NotBlank
    private boolean insurance;

    @NotBlank
    private boolean closed;


}
