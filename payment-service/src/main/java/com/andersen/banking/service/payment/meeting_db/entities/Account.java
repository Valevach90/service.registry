package com.andersen.banking.service.payment.meeting_db.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@EqualsAndHashCode
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, length = 34)
    private String accountNumber;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "bank_name", nullable = false, length = 30)
    private String bankName;

    @Column(name = "balance", nullable = false)
    private long balance;

}
