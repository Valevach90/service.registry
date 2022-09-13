package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode
@Getter @Setter
@Entity
@Table(name = "card_product")
@NoArgsConstructor
public class CardProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "max_cashback")
    private int maxCashback;

    @Column(name = "price")
    private double price;

    @Column(name = "advantages", nullable = false)
    private String advantages;

    @Column(name = "bank_partners", nullable = false)
    private String bankPartners;

    @Column(name = "loyalty_program", nullable = false)
    private String loyaltyProgram;
}
