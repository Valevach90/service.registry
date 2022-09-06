package com.andersen.banking.meeting_api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for accounts")
public class AccountDto {

    @JsonProperty("id")
    private UUID id;

    @NotBlank
    @JsonProperty("account_number")
    //@Pattern(regexp = "\\d+")
    private String accountNumber;

    @NotNull
    @JsonProperty("open_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;

    @NotNull
    @JsonProperty(value = "close_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closeDate = null;


    @Min(1L)
    @NotNull
    @JsonProperty("owner_id")
    private UUID ownerId;

    @NotBlank
    @JsonProperty("currency")
    @Size(min = 3, max = 3, message = "currency should have exactly 3 characters")
    private String currency;

    @NotBlank
    @JsonProperty("bank_name")
    @Size(min = 3, max = 30, message = "bank_name should have at least 3 and at maximum 30 characters")
    private String bankName;

    @NotNull
    @JsonProperty("balance")
    private double balance;

}
