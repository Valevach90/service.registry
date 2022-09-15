package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.CalculationMode;
import com.andersen.banking.OpenApiConstants;
import com.andersen.banking.meeting_db.entity.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for credit product")
public class CreditProductDTO {

    @Schema(description = OpenApiConstants.DESCRIPTION_CREDIT_PRODUCT_ID, example =
        OpenApiConstants.EXAMPLE_CREDIT_PRODUCT_ID)
    @JsonProperty("id")
    @NotNull(message = "Credit product id can't be null")
    private UUID uuid;


    private String name;

    private BigDecimal minSum;


    private BigDecimal maxSum;



    private Currency currency;


    private Double minLoanRate;


    private Double maxLoanRate;


    private Boolean needGuarantee;


    private Boolean earlyRepayment;


    private Integer minTerm;


    private Integer maxTerm;


    private String description;


    @Enumerated(EnumType.STRING)
    private CalculationMode calculationMode;


    private Integer gracePeriodMonth;


    private Boolean needIncomeStatement;
}
