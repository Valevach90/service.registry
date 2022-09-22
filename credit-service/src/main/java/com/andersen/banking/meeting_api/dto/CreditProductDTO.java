package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.andersen.banking.meeting_db.entity.CalculationMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for credit product")
public class CreditProductDTO {

    @Schema(description = OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT_ID,
        example = OpenApiConstants.EXAMPLE_ID_NULL)
    @JsonProperty("id")
    @JsonIgnore
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
    @NotNull
    private BigDecimal minLoanRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_MAX_LOAN_RATE,
        example = OpenApiConstants.EXAMPLE_RATE_DOUBLE)
    @JsonProperty("max_loan_rate")
    @NotNull
    private BigDecimal maxLoanRate;

    @Schema(description = OpenApiConstants.DESCRIPTION_NEED_GUARANTEE,
        example = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("need_guarantee")
    @NotNull
    private Boolean needGuarantee;

    @Schema(description = OpenApiConstants.DESCRIPTION_EARLY_REPAYMENT,
        example = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("early_repayment")
    @NotNull
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
    @NotNull
    private Integer gracePeriodMonth;

    @Schema(description = OpenApiConstants.DESCRIPTION_INCOME_STATEMENT,
        example = OpenApiConstants.EXAMPLE_BOOLEAN)
    @JsonProperty("need_income_statement")
    @NotNull
    private Boolean needIncomeStatement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinSum() {
        return minSum;
    }

    public void setMinSum(BigDecimal minSum) {
        this.minSum = minSum;
    }

    public BigDecimal getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(BigDecimal maxSum) {
        this.maxSum = maxSum;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
    }

    public BigDecimal getMinLoanRate() {
        return minLoanRate;
    }

    public void setMinLoanRate(BigDecimal minLoanRate) {
        this.minLoanRate = minLoanRate;
    }

    public BigDecimal getMaxLoanRate() {
        return maxLoanRate;
    }

    public void setMaxLoanRate(BigDecimal maxLoanRate) {
        this.maxLoanRate = maxLoanRate;
    }

    public Boolean getNeedGuarantee() {
        return needGuarantee;
    }

    public void setNeedGuarantee(Boolean needGuarantee) {
        this.needGuarantee = needGuarantee;
    }

    public Boolean getEarlyRepayment() {
        return earlyRepayment;
    }

    public void setEarlyRepayment(Boolean earlyRepayment) {
        this.earlyRepayment = earlyRepayment;
    }

    public Integer getMinTerm() {
        return minTerm;
    }

    public void setMinTerm(Integer minTerm) {
        this.minTerm = minTerm;
    }

    public Integer getMaxTerm() {
        return maxTerm;
    }

    public void setMaxTerm(Integer maxTerm) {
        this.maxTerm = maxTerm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CalculationMode getCalculationMode() {
        return calculationMode;
    }

    public void setCalculationMode(CalculationMode calculationMode) {
        this.calculationMode = calculationMode;
    }

    public Integer getGracePeriodMonth() {
        return gracePeriodMonth;
    }

    public void setGracePeriodMonth(Integer gracePeriodMonth) {
        this.gracePeriodMonth = gracePeriodMonth;
    }

    public Boolean getNeedIncomeStatement() {
        return needIncomeStatement;
    }

    public void setNeedIncomeStatement(Boolean needIncomeStatement) {
        this.needIncomeStatement = needIncomeStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditProductDTO that = (CreditProductDTO) o;
        return Objects.equals(name, that.name)
            && Objects.equals(minSum, that.minSum) && Objects.equals(maxSum,
            that.maxSum) && Objects.equals(currency, that.currency)
            && Objects.equals(minLoanRate, that.minLoanRate) && Objects.equals(
            maxLoanRate, that.maxLoanRate) && Objects.equals(needGuarantee,
            that.needGuarantee) && Objects.equals(earlyRepayment, that.earlyRepayment)
            && Objects.equals(minTerm, that.minTerm) && Objects.equals(maxTerm,
            that.maxTerm) && Objects.equals(description, that.description)
            && calculationMode == that.calculationMode && Objects.equals(gracePeriodMonth,
            that.gracePeriodMonth) && Objects.equals(needIncomeStatement,
            that.needIncomeStatement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, minSum, maxSum, currency, minLoanRate, maxLoanRate,
            needGuarantee, earlyRepayment, minTerm, maxTerm, description, calculationMode,
            gracePeriodMonth, needIncomeStatement);
    }
}
