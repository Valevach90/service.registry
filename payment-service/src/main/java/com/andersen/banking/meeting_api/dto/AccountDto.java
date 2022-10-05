package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_ACCOUNT_NUMBER;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_BALANCE;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_BANKNAME;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_CURRENCY;
import static com.andersen.banking.meeting_api.util.OpenApiConstants.EXAMPLE_UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for accounts")
public class AccountDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @Schema(example = EXAMPLE_ACCOUNT_NUMBER, defaultValue = EXAMPLE_ACCOUNT_NUMBER)
    @JsonProperty("account_number")
    private String accountNumber;

    @NotNull
    @JsonProperty("open_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;

    @NotNull
    @JsonProperty(value = "close_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closeDate;

    @NotNull
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("owner_id")
    private UUID ownerId;

    @NotBlank
    @JsonProperty("currency")
    @Schema(example = EXAMPLE_CURRENCY, defaultValue = EXAMPLE_CURRENCY)
    @Size(min = 3, max = 3, message = "currency should have exactly 3 characters")
    private String currency;

    @NotBlank
    @JsonProperty("bank_name")
    @Schema(example = EXAMPLE_BANKNAME, defaultValue = EXAMPLE_BANKNAME)
    @Size(
            min = 3,
            max = 30,
            message = "bank_name should have at least 3 and at maximum 30 characters")
    private String bankName;

    @NotNull
    @Schema(example = EXAMPLE_BALANCE, defaultValue = EXAMPLE_BALANCE)
    @JsonProperty("balance")
    private Long balance;

    @JsonProperty("is_active")
    private boolean isActive;
}
