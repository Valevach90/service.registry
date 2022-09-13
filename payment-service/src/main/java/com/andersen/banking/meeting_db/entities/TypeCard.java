package com.andersen.banking.meeting_db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "type_card")
@NoArgsConstructor
@EqualsAndHashCode
public class TypeCard {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "payment_system", nullable = false, length = 30)
    private String paymentSystem;

    @Column(name = "type_name", nullable = false, length = 30)
    private String typeName;
}
