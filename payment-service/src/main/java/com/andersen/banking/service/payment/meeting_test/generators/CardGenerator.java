package com.andersen.banking.service.payment.meeting_test.generators;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CardGenerator {

  private static final AtomicLong counter = new AtomicLong(1L);
  private Faker faker;

  public Card generateCard(Account account) {
    Card card = new Card();
    card.setId(counter.getAndIncrement());
    card.setAccount(account);
    card.setCardNumber(faker.regexify("[0-9]{16}"));
    card.setPinCode(faker.regexify("[0-9]{4}"));
    card.setExpirationDate(LocalDate.now().plusYears(faker.number().numberBetween(2, 5)));
    card.setHolderName("[a-z]{5,255}");

    return card;
  }

  @PostConstruct
  private void init() {
    faker = new Faker();
  }
}
