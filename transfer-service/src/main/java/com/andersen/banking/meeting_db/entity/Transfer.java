package com.andersen.banking.meeting_db.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Getter
@Setter
@Builder
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

    @Column(name = "amount", nullable = false)
    private Long amount;

    @JoinColumn(name = "currency_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer transfer)) return false;
        if (!super.equals(o)) return false;
        return status == transfer.status && userId.equals(transfer.userId)
                && sourcePaymentType.equals(transfer.sourcePaymentType)
                && sourceNumber.equals(transfer.sourceNumber)
                && destinationPaymentType.equals(transfer.destinationPaymentType)
                && destinationNumber.equals(transfer.destinationNumber)
                && amount.equals(transfer.amount)
                && currency.equals(transfer.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, sourcePaymentType, sourceNumber, destinationPaymentType,
                destinationNumber, amount, currency, status);
    }
}
