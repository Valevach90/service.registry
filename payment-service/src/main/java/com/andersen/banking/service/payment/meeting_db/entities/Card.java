package com.andersen.banking.service.payment.meeting_db.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class presents an entity, which will be stored in the database.
 */

@Getter
@Setter
@Entity
@Table(name = "cards")
@NoArgsConstructor
@ToString
public class Card {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "card_number", nullable = false)
  private String cardNumber;

  @Column(name = "exp_date", nullable = false)
  private LocalDate expirationDate;

  @Column(name = "pin_code", nullable = false)
  private String pinCode;

  @Column(name = "holder_name", nullable = false)
  private String holderName;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;
}
