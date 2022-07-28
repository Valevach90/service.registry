package com.andersen.banking.deposit_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * Transfer entity for Deposit service.
 */

@Data
@Entity
@Table(name = "transfers")
@NoArgsConstructor
public class Transfer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_id", nullable = false)
    private Deposit deposit;

    @Column(name = "from_card_number", nullable = false)
    private String fromCardNumber;

    @Column(name = "to_card_number", nullable = false)
    private String toCardNumber;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "success_status", nullable = false)
    private Boolean successStatus;
}
