package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atm")
public class Atm {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "street_id")
    private Street street;

    @Column(nullable = false, name = "house_number")
    private Long houseNumber;

    @Column(name = "building")
    private String building;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BankBranch bankBranch;

    @Column(nullable = false, name = "round_the_clock")
    private Boolean roundTheClock;

    @Column(nullable = false, name = "work_at_weekend")
    private Boolean workAtWeekend;

    @Column(nullable = false, name = "cash_withdraw")
    private Boolean cashWithdraw;

    @Column(nullable = false, name = "currency_exchange")
    private Boolean currencyExchange;

    @Column(nullable = false, name = "is_active")
    private Boolean isActive;

}
