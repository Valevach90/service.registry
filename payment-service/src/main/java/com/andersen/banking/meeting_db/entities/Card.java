package com.andersen.banking.meeting_db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/** This class presents an entity, which will be stored in the database. */
@Getter
@Setter
@Entity
@Table(name = "card")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Card {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(
            cascade = {
                CascadeType.MERGE,
                CascadeType.REMOVE,
                CascadeType.REFRESH,
                CascadeType.DETACH
            })
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "valid_from_date", nullable = false)
    private LocalDate validFromDate;

    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    @Column(name = "first_twelve_numbers", nullable = false, length = 64)
    private String firstTwelveNumbers;

    @Column(name = "last_four_numbers", nullable = false, length = 4)
    private String lastFourNumbers;

    @Column(name = "holder_name", nullable = false, length = 30)
    private String holderName;

    @ManyToOne
    @JoinColumn(name = "type_card_id", referencedColumnName = "id")
    private TypeCard typeCard;
}
