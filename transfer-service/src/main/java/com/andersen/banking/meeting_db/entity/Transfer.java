package com.andersen.banking.meeting_db.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfer")
public class Transfer extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_payment_type_id", nullable = false)
    private PaymentType sourcePaymentType;

    @Column(name = "source_number", nullable = false)
    private String sourceNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_payment_type_id", nullable = false)
    private PaymentType destinationPaymentType;

    @Column(name = "destination_number", nullable = false)
    private String destinationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_status_id", nullable = false)
    private TransferStatus transferStatus;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @JoinColumn(name = "currency_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

}
