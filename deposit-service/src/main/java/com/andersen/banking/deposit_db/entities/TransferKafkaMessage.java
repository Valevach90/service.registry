package com.andersen.banking.deposit_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "transfer_message")
@NoArgsConstructor
public class TransferKafkaMessage {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "source_number", nullable = false)
    private String sourceNumber;

    @Column(name = "source_type", nullable = false)
    private String sourceType;

    @Column(name = "destination_number", nullable = false)
    private String destinationNumber;

    @Column(name = "destination_type", nullable = false)
    private String destinationType;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Column(name = "status_description", nullable = false)
    private String statusDescription;
}
