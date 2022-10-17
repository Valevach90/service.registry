package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "linked_cards")
@NoArgsConstructor
public class LinkedCard {

    @Id
    private UUID id;

    @Column(name = "first_twelve_numbers", nullable = false, length = 64)
    private String firstTwelveNumbers;

    @Column(name = "last_four_numbers", nullable = false, length = 4)
    private String lastFourNumbers;

    @Column(name = "flag_for_transferring", nullable = false)
    private Boolean flagForTransferring;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "deposit_id", referencedColumnName = "id")
    @ToString.Exclude
    private Deposit deposit;
}
