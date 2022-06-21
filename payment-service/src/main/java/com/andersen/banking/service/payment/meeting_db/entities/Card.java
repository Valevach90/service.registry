package com.andersen.banking.service.payment.meeting_db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * This class presents an entity, which will be stored in the database.
 */

@Getter
@Setter
@Entity
@Table(name = "cards")
@NoArgsConstructor
@EqualsAndHashCode
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "valid_from_date", nullable = false)
    private Date validFromDate;

    @Column(name = "expire_date", nullable = false)
    private Date expireDate;

    @Column(name = "first_twelve_numbers", nullable = false, length = 64)
    private String firstTwelveNumbers;

    @Column(name = "last_four_numbers", nullable = false, length = 4)
    private String lastFourNumbers;

    @Column(name = "holder_name", nullable = false, length = 30)
    private String holderName;

}
