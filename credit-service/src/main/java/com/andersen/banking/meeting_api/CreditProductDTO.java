package com.andersen.banking.meeting_api;

import com.andersen.banking.CalculationMode;
import com.andersen.banking.meeting_db.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for credit product")
public class CreditProductDTO {

    @Id
    private UUID uuid;


    private String name;

    private BigDecimal minSum;


    private BigDecimal maxSum;



    private Currency currency;


    private BigDecimal minLoanRate;


    private BigDecimal maxLoanRate;


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
