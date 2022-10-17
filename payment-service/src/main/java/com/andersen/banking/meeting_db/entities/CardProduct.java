package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode
@Getter @Setter
@Entity
@Table(name = "card_product")
@ToString
@NoArgsConstructor
public class CardProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "cashback", nullable = false)
    private Integer cashback;

    @Column(name = "price")
    private Double price;

    @Column(name = "advantages", nullable = false)
    private String advantages;

    @Column(name = "bank_partners", nullable = false)
    private String bankPartners;

    @Column(name = "loyalty_program", nullable = false)
    private String loyaltyProgram;

    @Column(name = "bank_partners_mini")
    private String bankPartnersMini;

    @Column(name = "loyalty_program_mini")
    private String loyaltyProgramMini;

    @ManyToOne
    @JoinColumn(name = "type_card_id", referencedColumnName = "id")
    private TypeCard typeCard;
}
