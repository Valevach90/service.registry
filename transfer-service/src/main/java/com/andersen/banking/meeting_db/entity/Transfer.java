package com.andersen.banking.meeting_db.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfer")
public class Transfer extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private UUID userId;

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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transfer transfer = (Transfer) o;
        return sourcePaymentType.equals(transfer.sourcePaymentType) && sourceNumber.equals(transfer.sourceNumber)
                && destinationPaymentType.equals(transfer.destinationPaymentType)
                && destinationNumber.equals(transfer.destinationNumber) && amount.equals(transfer.amount)
                && currency.equals(transfer.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), sourceNumber, destinationNumber, sourcePaymentType,
                destinationNumber, currency, amount);
    }
}
