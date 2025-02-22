package com.andersen.banking.meeting_db.entities;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_branch")
public class BankBranch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(nullable = false, name = "street_id")
    private Street street;

    @Column(nullable = false, name = "house")
    private String house;

    @Column(name = "building")
    private String building;

    @Column(nullable = false, name = "branch_number", length = 5)
    private String branchNumber;

    @Column(nullable = false, name = "branch_coordinates", length = 20)
    private String branchCoordinates;

    @Column(nullable = false, name = "work_at_weekend")
    private boolean workAtWeekend;

    @Column(nullable = false, name = "cash_withdraw")
    private boolean cashWithdraw;

    @Column(nullable = false, name = "money_transfer")
    private boolean moneyTransfer;

    @Column(nullable = false, name = "accept_payment")
    private boolean acceptPayment;

    @Column(nullable = false, name = "currency_exchange")
    private boolean currencyExchange;

    @Column(nullable = false, name = "exotic_currency")
    private boolean exoticCurrency;

    @Column(nullable = false, name = "ramp")
    private boolean ramp;

    @Column(nullable = false, name = "replenish_card")
    private boolean replenishCard;

    @Column(nullable = false, name = "replenish_account")
    private boolean replenishAccount;

    @Column(nullable = false, name = "consultation")
    private boolean consultation;

    @Column(nullable = false, name = "insurance")
    private boolean insurance;

    @Column(nullable = false, name = "is_closed")
    private boolean closed;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "bankBranch",
            cascade = {CascadeType.PERSIST})
    private List<Atm> atms;
}
