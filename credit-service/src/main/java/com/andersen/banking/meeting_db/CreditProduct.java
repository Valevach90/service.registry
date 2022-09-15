package com.andersen.banking.meeting_db;

import com.andersen.banking.CalculationMode;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CreditProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name ="id")
    private UUID uuid;

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "min_sum")
    private BigDecimal minSum;

    @Column(name = "max_sum")
    private BigDecimal maxSum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "min_loan_rate")
    private BigDecimal minLoanRate;

    @Column(name = "max_loan_rate")
    private BigDecimal maxLoanRate;

    @Column(name = "need_guarantee")
    private Boolean needGuarantee;

    @Column(name = "early_repayment")
    private Boolean earlyRepayment;

    @Column(name = "min_term")
    private Integer minTerm;

    @Column(name = "max_term")
    private Integer maxTerm;

    @Column(name = "description")
    private String description;

    @Column(name = "calculation_mode")
    @Enumerated(EnumType.STRING)
    private CalculationMode calculationMode;

    @Column(name = "grace_period_month")
    private Integer gracePeriodMonth;

    @Column(name = "need_income_statement")
    private Boolean needIncomeStatement;
}
