package com.andersen.banking.meeting_db.entities;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Transfer entity for Deposit service.
 */

@Entity
@Getter
@Setter
@Builder
@Table(name = "transfers")
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    @Id
    @Column(name = "transfer_id", nullable = false)
    private UUID transferId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

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

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "status")
    private Integer status;

    @Column(name = "status_description")
    private String statusDescription;
}


