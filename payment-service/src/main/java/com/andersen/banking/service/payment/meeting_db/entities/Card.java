package com.andersen.banking.service.payment.meeting_db.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class presents an entity, which will be stored in the database.
 */

@Getter
@Setter
@Entity
@Table(name = "card")
@NoArgsConstructor
@EqualsAndHashCode
public class Card {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  @Column(name = "valid_from_date", nullable = false)
  private LocalDate validFromDate;

  @Column(name = "expire_date", nullable = false)
  private LocalDate expireDate;

  @Column(name = "first_twelve_numbers", nullable = false, length = 64)
  private String firstTwelveNumbers;

  @Column(name = "last_four_numbers", nullable = false, length = 4)
  private String lastFourNumbers;

  @Column(name = "holder_name", nullable = false, length = 30)
  private String holderName;

}
