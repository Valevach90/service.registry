package com.andersen.banking.meeting_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Delivery order is object that allows to create order for delivery of card.
 */

@Data
@Entity
@Table(name = "delivery_order")
@NoArgsConstructor
public class DeliveryOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_type_id", nullable = false)
    private DeliveryType deliveryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id", nullable = false)
    private DeliveryAddress deliveryAddress;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "card_id")
    private UUID cardId;

    @Column(name = "opening_time", nullable = false)
    private Timestamp openingTime;

    @Column(name = "lead_time")
    private Timestamp leadTime;

    @Column(name = "is_delivered", nullable = false)
    private Boolean isDelivered;

    @Column(name = "status_description")
    private String statusDescription;
}
