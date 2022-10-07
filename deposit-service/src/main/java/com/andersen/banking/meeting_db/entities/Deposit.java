package com.andersen.banking.meeting_db.entities;

import java.util.ArrayList;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

/**
 * Deposit entity.
 */

@Data
@Entity
@Table(name = "deposits")
@NoArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "deposit_number", nullable = false)
    private String depositNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_product_id", nullable = false)
    private DepositProduct depositProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_type_id", nullable = false)
    private DepositType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "term_months")
    private Integer termMonths;

    @Column(name = "open_date", nullable = false)
    private Date openDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "interest_rate", nullable = false)
    private Integer interestRate;

    @Column(name = "fixed_interest", nullable = false)
    private Boolean fixedInterest;

    @Column(name = "subsequent_replenishment", nullable = false)
    private Boolean subsequentReplenishment;

    @Column(name = "early_withdrawal", nullable = false)
    private Boolean earlyWithdrawal;

    @Column(name = "interest_withdrawal", nullable = false)
    private Boolean interestWithdrawal;

    @Column(name = "capitalization", nullable = false)
    private Boolean capitalization;

    @Column(name = "is_revocable")
    private Boolean isRevocable;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "deposit",
            cascade = CascadeType.ALL)
    private List<LinkedCard> linkedCards = new ArrayList<>();

}
