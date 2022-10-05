package com.andersen.banking.meeting_api.dto;

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
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for accounts")
public class AccountChangesResponseDto {

    @Schema(hidden = true)
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("open_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;

    @NotNull
    @JsonProperty(value = "close_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closeDate = null;

    @NotNull
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
    @JsonProperty("owner_id")
    private UUID ownerId;

    @NotBlank
    @JsonProperty("currency")
    @Schema(example = EXAMPLE_CURRENCY, defaultValue = EXAMPLE_CURRENCY)
    private String currency;

    @NotBlank
    @JsonProperty("bank_name")
    @Schema(example = EXAMPLE_BANKNAME, defaultValue = EXAMPLE_BANKNAME)
    private String bankName;

    @NotNull
    @Schema(example = EXAMPLE_BALANCE, defaultValue = EXAMPLE_BALANCE)
    @JsonProperty("balance")
    private double balance;

    @NotNull
    @Schema(example = "true", defaultValue = "true")
    @JsonProperty("is_active")
    private boolean isActive;

    @NotNull
    @Schema(example = "UPDATE", defaultValue = "UPDATE")
    @JsonProperty("revision_type")
    private String revisionType;

    @NotNull
    @Schema(example = "0", defaultValue = "0")
    @JsonProperty("timestamp")
    private Long timestamp;
}
