package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "linked_cards")
@NoArgsConstructor
public class LinkedCard {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name ="id")
    private UUID id;

    @Column(name = "first_twelve_numbers", nullable = false, length = 64)
    private String firstTwelveNumbers;

    @Column(name = "last_four_numbers", nullable = false, length = 4)
    private String lastFourNumbers;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="deposit_id")
    private Deposit deposit;
}
