package com.andersen.banking.service.payment.meeting_db.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "regular_payment")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegularPayment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_description", nullable = false)
    private String description;

    @Column(name = "first_date", nullable = false)
    private Date firstDate;

    @Column(name = "last_date", nullable = false)
    private Date lastDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Card sourceCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Card recipientCardId;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "frequency", nullable = false)
    private String frequency;
}
