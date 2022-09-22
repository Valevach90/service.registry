package com.andersen.banking.meeting_db.entity;

import java.math.BigDecimal;
import java.util.Objects;
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_products")
public class CreditProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "product_name", nullable = false)
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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
        CreditProduct that = (CreditProduct) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name)
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
        return Objects.hash(uuid, name, minSum, maxSum, currency, minLoanRate, maxLoanRate,
            needGuarantee, earlyRepayment, minTerm, maxTerm, description, calculationMode,
            gracePeriodMonth, needIncomeStatement);
    }
}
