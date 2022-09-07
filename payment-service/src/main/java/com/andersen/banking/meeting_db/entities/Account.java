package com.andersen.banking.meeting_db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Account {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "account_number", nullable = false, length = 34)
    private String accountNumber;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "bank_name", nullable = false, length = 30)
    private String bankName;

    @Column(name = "balance", nullable = false)
    private long balance;
}
