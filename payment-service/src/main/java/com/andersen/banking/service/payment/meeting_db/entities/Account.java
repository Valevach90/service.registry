package com.andersen.banking.service.payment.meeting_db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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
    private Date openDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "bank_name", nullable = false, length = 30)
    private String bankName;

    @Column(name = "balance", nullable = false)
    private long balance;

}
