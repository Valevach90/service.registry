package com.andersen.banking.service.payment.meeting_db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usr_id", nullable = false)
    private Long userId;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;


    @Column(name = "termination_date") //termination date can be null because that field will be set after closing account
    private Date terminationDate;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "currency", nullable = false)
    private String currency;

}
