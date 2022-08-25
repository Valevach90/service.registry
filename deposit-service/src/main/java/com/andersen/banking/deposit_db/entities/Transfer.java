package com.andersen.banking.deposit_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Transfer entity for Deposit service.
 */

@Data
@Entity
@Table(name = "transfers")
@NoArgsConstructor
public class Transfer {

    @Id
    @Column(name = "transfer_id", nullable = false)
    private UUID transferId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;

    @Column(name = "source_number", nullable = false)
    private String sourceNumber;

    @Column(name = "source_type", nullable = false)
    private String sourceType;

    @Column(name = "destination_number", nullable = false)
    private String destinationNumber;

    @Column(name = "destination_type", nullable = false)
    private String destinationType;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "result")
    private Boolean result;

    @Column(name = "status_description")
    private String statusDescription;
}
