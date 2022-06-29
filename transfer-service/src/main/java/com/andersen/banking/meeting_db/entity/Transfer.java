package com.andersen.banking.meeting_db.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tables")
public class Transfer extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "number_from")
    private String sourceNumber;

    @Column(name = "number_to")
    private String destinationNumber;

    @Column(name = "number_fro")
    private String sourceType;

    @Column(name = "destinationType")
    private String destinationType;

    @ManyToOne
    @Column(name = "transferStatus")
    private TransferStatus transferStatus;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "currency")
    private String currency;


}
