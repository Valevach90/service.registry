package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Deposit Product entity which represents banking Deposit Products.
 */

@Data
@Entity
@Table(name = "deposit_products")
@NoArgsConstructor
public class DepositProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name ="id")
    private UUID id;

    @Column(name = "deposit_name", nullable = false)
    private String depositName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_type_id", nullable = false)
    private DepositType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "min_term_months")
    private Integer minTermMonths;

    @Column(name = "max_term_months")
    private Integer maxTermMonths;

    @Column(name = "min_amount", nullable = false)
    private Long minAmount;

    @Column(name = "max_amount", nullable = false)
    private Long maxAmount;

    @Column(name = "min_interest_rate", nullable = false)
    private Integer minInterestRate;

    @Column(name = "max_interest_rate", nullable = false)
    private Integer maxInterestRate;

    @Column(name = "fixed_interest")
    private Boolean fixedInterest;

    @Column(name = "subsequent_replenishment")
    private Boolean subsequentReplenishment;

    @Column(name = "early_withdrawal")
    private Boolean earlyWithdrawal;

    @Column(name = "interest_withdrawal")
    private Boolean interestWithdrawal;

    @Column(name = "capitalization")
    private Boolean capitalization;

    @Column(name = "is_revocable")
    private Boolean isRevocable;

    @Column(name = "is_customizable", nullable = false)
    private Boolean isCustomizable;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "description")
    private String description;
}
