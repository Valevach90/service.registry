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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "city_id", nullable = false)
    private DeliveryCity city;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "street_id", nullable = false)
    private DeliveryStreet street;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;
}
