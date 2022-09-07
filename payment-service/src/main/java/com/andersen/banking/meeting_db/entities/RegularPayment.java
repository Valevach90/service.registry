package com.andersen.banking.meeting_db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "regular_payment")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegularPayment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "payment_description", nullable = false)
    private String description;

    @Column(name = "first_date", nullable = false)
    private LocalDate firstDate;

    @Column(name = "last_date", nullable = false)
    private LocalDate lastDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Card sourceCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Card recipientCard;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "frequency", nullable = false)
    private String frequency;
}
