package com.andersen.banking.meeting_api.dto.bank_brunch;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_COORDINATES;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_LONG;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_STREET;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBranchDto {

    @NotNull
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @NotNull(message = "id can't be null")
    private Long id;

    @NotNull
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @NotNull(message = "city id can't be null")
    private Long cityId;

    @NotBlank
    @Schema(example = EXAMPLE_STREET, defaultValue = EXAMPLE_STREET)
    @NotNull(message = "street name can't be null")
    private String streetName;

    @NotBlank
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @NotNull(message = "house can't be null")
    private String house;

    @NotBlank
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @NotNull(message = "building can't be null")
    private String building;

    @NotBlank
    @Schema(example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @NotNull(message = "branch number can't be null")
    private String branchNumber;

    @NotBlank
    @Schema(example = EXAMPLE_COORDINATES, defaultValue = EXAMPLE_COORDINATES)
    @NotNull(message = "branch coordinates can't be null")
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
