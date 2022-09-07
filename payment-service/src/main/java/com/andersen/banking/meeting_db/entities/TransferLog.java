package com.andersen.banking.meeting_db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "transfer_log")
public class TransferLog {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "source_payment_type", nullable = false)
    private String sourcePaymentType;

    @Column(name = "source_number", nullable = false)
    private String sourceNumber;

    @Column(name = "destination_payment_type", nullable = false)
    private String destinationPaymentType;

    @Column(name = "destination_number", nullable = false)
    private String destinationNumber;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferLog)) return false;
        TransferLog that = (TransferLog) o;
        return id.equals(that.id)
                && userId.equals(that.userId)
                && sourcePaymentType.equals(that.sourcePaymentType)
                && sourceNumber.equals(that.sourceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, sourcePaymentType, sourceNumber);
    }
}
