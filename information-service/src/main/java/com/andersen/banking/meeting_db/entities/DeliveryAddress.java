package com.andersen.banking.meeting_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Delivery address represents address for delivery of card.
 */

@Data
@Entity
@Table(name = "delivery_address")
@NoArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;
}
