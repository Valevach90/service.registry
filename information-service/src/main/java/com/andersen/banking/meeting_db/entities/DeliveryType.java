package com.andersen.banking.meeting_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Delivery type represents types of card delivery.
 */

@Data
@Entity
@Table(name = "delivery_type")
@NoArgsConstructor
public class DeliveryType {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "type_name", nullable = false)
    private String typeName;
}
