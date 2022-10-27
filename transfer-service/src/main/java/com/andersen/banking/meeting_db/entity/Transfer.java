package com.andersen.banking.meeting_db.entity;


import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Column(name = "service")
    private String service;

    @Column(name = "regular_id")
    private UUID regularId;;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Transfer transfer = (Transfer) o;
        return sourcePaymentType.equals(transfer.sourcePaymentType) && sourceNumber.equals(
                transfer.sourceNumber)
                && destinationPaymentType.equals(transfer.destinationPaymentType)
                && destinationNumber.equals(transfer.destinationNumber) && amount.equals(
                transfer.amount)
                && currency.equals(transfer.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), sourceNumber, destinationNumber, sourcePaymentType,
                destinationNumber, currency, amount);
    }
}
