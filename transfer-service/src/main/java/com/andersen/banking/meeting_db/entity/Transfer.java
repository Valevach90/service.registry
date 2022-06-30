package com.andersen.banking.meeting_db.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tables")
public class Transfer extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_payment_type_id", nullable = false)
    private PaymentType sourcePaymentType;

    @Column(name = "number_to", nullable = false)
    private String destinationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_payment_type_id", nullable = false)
    private PaymentType destinationPaymentType;

    @Column(name = "destinationType", nullable = false)
    private String destinationType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "transfer_status_id", nullable = false)
    private TransferStatus transferStatus;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @JoinColumn(name = "currency_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transfer transfer = (Transfer) o;
        return userId.equals(transfer.userId) && sourcePaymentType.equals(transfer.sourcePaymentType) && destinationNumber.equals(transfer.destinationNumber) && destinationPaymentType.equals(transfer.destinationPaymentType) && destinationType.equals(transfer.destinationType) && transferStatus.equals(transfer.transferStatus) && amount.equals(transfer.amount) && currency.equals(transfer.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, sourcePaymentType, destinationNumber, destinationPaymentType, destinationType, transferStatus, amount, currency);
    }
}
