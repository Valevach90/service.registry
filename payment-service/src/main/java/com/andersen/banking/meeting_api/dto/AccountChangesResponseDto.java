package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.util.OpenApiConstants.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private LocalDate closeDate;

    @NotNull
    @JsonProperty("owner_id")
    @Schema(example = EXAMPLE_UUID, defaultValue = EXAMPLE_UUID)
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
    @JsonProperty("balance")
    @Schema(example = EXAMPLE_BALANCE, defaultValue = EXAMPLE_BALANCE)
    private double balance;

    @NotNull
    @JsonProperty("is_active")
    private boolean isActive;

    @NotNull
    @JsonProperty("revision_type")
    @Schema(example = EXAMPLE_REVISION_TYPE, defaultValue = EXAMPLE_REVISION_TYPE)
    private String revisionType;

    @NotNull
    @JsonProperty("timestamp")
    @Schema(example = EXAMPLE_TIMESTAMP, defaultValue = EXAMPLE_TIMESTAMP)
    private Long timestamp;
}
