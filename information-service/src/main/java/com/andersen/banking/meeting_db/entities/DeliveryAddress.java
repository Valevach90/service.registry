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

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private Street street;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;
}
