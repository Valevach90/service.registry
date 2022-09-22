package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.andersen.banking.meeting_db.entity.CalculationMode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "response dto for credit product")
public class CreditProductResponseDTO {

    @Schema(description = OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT_ID,
        example = OpenApiConstants.EXAMPLE_ID)
    @JsonProperty("id")
    @NotNull(message = "Credit product id can't be null")
    private UUID uuid;

    @Schema(description = OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT_NAME,
        example = OpenApiConstants.EXAMPLE_CREDIT_PRODUCT_NAME)
    @JsonProperty("credit_name")
    @NotNull(message = "Credit product name can't be null")
    private String name;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_SUM,
        example = OpenApiConstants.EXAMPLE_SUM_DECIMAL)
    @JsonProperty("min_sum")
    @NotNull(message = "Minimal sum of credit can't be null")
    private BigDecimal minSum;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_SUM,
        example = OpenApiConstants.EXAMPLE_SUM_DECIMAL)
    @JsonProperty("max_sum")
    @NotNull(message = "Maximum sum of credit can't be null")
    private BigDecimal maxSum;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null")
    private CurrencyDTO currency;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_LOAN_RATE,
        example = OpenApiConstants.EXAMPLE_RATE_DOUBLE)
    @JsonProperty("min_loan_rate")
    private BigDecimal minLoanRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_LOAN_RATE,
        example = OpenApiConstants.EXAMPLE_RATE_DOUBLE)
    @JsonProperty("max_loan_rate")
    private BigDecimal maxLoanRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_NEED_GUARANTEE,
        example = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("need_guarantee")
    private Boolean needGuarantee;

    @Schema(description = OpenApiConstants.DESCRIPTION_EARLY_REPAYMENT,
        example = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("early_repayment")
    private Boolean earlyRepayment;

    @Schema(description = OpenApiConstants.DESCRIPTION_MIN_TERM,
        example = OpenApiConstants.EXAMPLE_MONTHS_INTEGER)
    @JsonProperty("min_term")
    @NotNull(message = "Minimal term of credit can't be null")
    private Integer minTerm;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_TERM,
        example = OpenApiConstants.EXAMPLE_MONTHS_INTEGER)
    @JsonProperty("max_term")
    @NotNull(message = "Maximum term of credit can't be null")
    private Integer maxTerm;

    @Schema(description = OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT,
        example = OpenApiConstants.EXAMPLE_CREDIT_PRODUCT)
    @JsonProperty("description")
    @NotNull(message = "Description of credit can't be null")
    private String description;


    @Schema(description = OpenApiConstants.DESCRIPTION_CALCULATION_MODE,
        example = OpenApiConstants.EXAMPLE_CALCULATION_MODE)
    @JsonProperty("calculation_mode")
    @NotNull(message = "Calculation mode can't be null")
    private CalculationMode calculationMode;

    @Schema(description = OpenApiConstants.DESCRIPTION_GRACE_PERIOD,
        example = OpenApiConstants.EXAMPLE_MONTHS_INTEGER)
    @JsonProperty("grace_period_month")
    private Integer gracePeriodMonth;

    @Schema(description = OpenApiConstants.DESCRIPTION_INCOME_STATEMENT,
        example = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("need_income_statement")
    private Boolean needIncomeStatement;

}
