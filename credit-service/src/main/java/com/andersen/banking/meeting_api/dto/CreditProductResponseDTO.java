package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_CALCULATION_MODE;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT_ID;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT_NAME;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_EARLY_REPAYMENT;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_GRACE_PERIOD;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_INCOME_STATEMENT;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_MAX_LOAN_RATE;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_MAX_SUM;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_MAX_TERM;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_MIN_LOAN_RATE;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_MIN_SUM;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_MIN_TERM;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.DESCRIPTION_NEED_GUARANTEE;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_BOOLEAN;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_CALCULATION_MODE;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_CREDIT_PRODUCT;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_CREDIT_PRODUCT_NAME;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_ID;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_MONTHS_INTEGER;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_RATE_DOUBLE;
import static com.andersen.banking.meeting_api.utils.OpenApiConstants.EXAMPLE_SUM_DECIMAL;

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

    @Schema(description = DESCRIPTION_CREDIT_PRODUCT_ID,
        example = EXAMPLE_ID)
    @JsonProperty("id")
    @NotNull(message = "Credit product id can't be null")
    private UUID uuid;

    @Schema(description = DESCRIPTION_CREDIT_PRODUCT_NAME,
        example = EXAMPLE_CREDIT_PRODUCT_NAME)
    @JsonProperty("credit_name")
    @NotNull(message = "Credit product name can't be null")
    private String name;

    @Schema(description = DESCRIPTION_MIN_SUM,
        example = EXAMPLE_SUM_DECIMAL)
    @JsonProperty("min_sum")
    @NotNull(message = "Minimal sum of credit can't be null")
    private BigDecimal minSum;

    @Schema(description = DESCRIPTION_MAX_SUM,
        example = EXAMPLE_SUM_DECIMAL)
    @JsonProperty("max_sum")
    @NotNull(message = "Maximum sum of credit can't be null")
    private BigDecimal maxSum;

    @JsonProperty("currency")
    @NotNull(message = "Currency can't be null")
    private CurrencyResponseDTO currency;

    @Schema(description = DESCRIPTION_MIN_LOAN_RATE,
        example = EXAMPLE_RATE_DOUBLE)
    @JsonProperty("min_loan_rate")
    private BigDecimal minLoanRate;

    @Schema(description = DESCRIPTION_MAX_LOAN_RATE,
        example = EXAMPLE_RATE_DOUBLE)
    @JsonProperty("max_loan_rate")
    private BigDecimal maxLoanRate;

    @Schema(description = DESCRIPTION_NEED_GUARANTEE,
        example = EXAMPLE_BOOLEAN)
    @JsonProperty("need_guarantee")
    private Boolean needGuarantee;

    @Schema(description = DESCRIPTION_EARLY_REPAYMENT,
        example = EXAMPLE_BOOLEAN)
    @JsonProperty("early_repayment")
    private Boolean earlyRepayment;

    @Schema(description = DESCRIPTION_MIN_TERM,
        example = EXAMPLE_MONTHS_INTEGER)
    @JsonProperty("min_term")
    @NotNull(message = "Minimal term of credit can't be null")
    private Integer minTerm;

    @Schema(description = DESCRIPTION_MAX_TERM,
        example = EXAMPLE_MONTHS_INTEGER)
    @JsonProperty("max_term")
    @NotNull(message = "Maximum term of credit can't be null")
    private Integer maxTerm;

    @Schema(description = DESCRIPTION_CREDIT_PRODUCT,
        example = EXAMPLE_CREDIT_PRODUCT)
    @JsonProperty("description")
    @NotNull(message = "Description of credit can't be null")
    private String description;


    @Schema(description = DESCRIPTION_CALCULATION_MODE,
        example = EXAMPLE_CALCULATION_MODE)
    @JsonProperty("calculation_mode")
    @NotNull(message = "Calculation mode can't be null")
    private CalculationMode calculationMode;

    @Schema(description = DESCRIPTION_GRACE_PERIOD,
        example = EXAMPLE_MONTHS_INTEGER)
    @JsonProperty("grace_period_month")
    private Integer gracePeriodMonth;

    @Schema(description = DESCRIPTION_INCOME_STATEMENT,
        example = EXAMPLE_BOOLEAN)
    @JsonProperty("need_income_statement")
    private Boolean needIncomeStatement;

}
