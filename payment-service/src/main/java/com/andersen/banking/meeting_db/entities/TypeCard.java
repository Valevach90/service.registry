package com.andersen.banking.meeting_db.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "type_card")
@NoArgsConstructor
@EqualsAndHashCode
public class TypeCard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_system", nullable = false, length = 30)
    private String paymentSystem;

    @Column(name = "type_name", nullable = false, length = 30)
    private String typeName;
}
