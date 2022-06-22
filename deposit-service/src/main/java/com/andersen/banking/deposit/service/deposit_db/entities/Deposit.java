package com.andersen.banking.deposit.service.deposit_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Deposit entity.
 */

@Data
@Entity
@Table(name = "deposits")
@NoArgsConstructor
public class Deposit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deposit_number", nullable = false)
    private String depositNumber;

    @Column(name = "deposit_name", nullable = false)
    private String depositName;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "term")
    private Integer term;

    @Column(name = "open_date", nullable = false)
    private Date openDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "interest", nullable = false)
    private Integer interest;

    @Column(name = "fixed_interest", nullable = false)
    private Boolean fixedInterest;

    @Column(name = "replenishment_card_number", nullable = false)
    private String replenishmentCardNumber;

    @Column(name = "subsequent_replenishment", nullable = false)
    private Boolean subsequentReplenishment;

    @Column(name = "withdrawal_card_number")
    private String withdrawalCardNumber;

    @Column(name = "early_withdrawal", nullable = false)
    private Boolean earlyWithdrawal;

    @Column(name = "interest_withdrawal", nullable = false)
    private Boolean interestWithdrawal;

    @Column(name = "capitalization", nullable = false)
    private Boolean capitalization;

    @Column(name = "early_closure", nullable = false)
    private Boolean earlyClosure;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
