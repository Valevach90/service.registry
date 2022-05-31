package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.repositories.CardRepository;
import com.andersen.banking.service.payment.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.exceptions.PaymentServiceException;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import java.time.LocalDate;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CardService implementation.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;

  @Transactional(readOnly = true)
  @Override
  public Card findById(Long id) {
    log.debug("Find card by id: {}", id);

    Card card = cardRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Card.class, id));

    log.debug("Card with id {} successfully found", id);
    return card;
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Card> findAll(Pageable pageable) {
    log.info("Find all cards for pageable: {}", pageable);

    Page<Card> allCards = cardRepository.findAll(pageable);

    log.info("Found {} cards", allCards.getContent().size());
    return allCards;
  }

  @Transactional
  @Override
  public Card update(Card card) {
    log.debug("Trying to update card: {}", card);

    validateCard(card);

    cardRepository.findById(card.getId())
        .orElseThrow(() -> new NotFoundException(Card.class, card.getId()));

    Card updatedCard = cardRepository.save(card);

    log.debug("Return updated card: {}", updatedCard);

    return updatedCard;
  }

  @Transactional
  @Override
  public Card deleteById(Long id) {
    log.info("Trying to delete card with id: {}", id);

    Card card = cardRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Card.class, id));

    cardRepository.deleteById(id);

    log.info("Deleted card with id: {}", id);
    return card;
  }

  @Transactional
  @Override
  public Card create(Card card) {
    log.info("Creating card: {}", card);

    validateCard(card);
    Card savedCard = cardRepository.save(card);

    log.info("Created card: {}", savedCard);
    return savedCard;
  }

  private void validateCard(Card card) {
    checkIfStringContainsOnlyNumbers(card.getCardNumber());
    checkIfStringContainsOnlyNumbers(card.getPinCode());
    checkIfDateIsLaterThanToday(card.getExpirationDate());
  }

  private void checkIfStringContainsOnlyNumbers(final String string) {

    final Long numbersCounter = Arrays.stream(string.split(""))
        .filter(x -> x.matches("[0-9]"))
        .count();

    if (string.length() != numbersCounter) {
      throw new PaymentServiceException(String.format("This parameter should contain only numbers: %s", string));
    }
  }

  private void checkIfDateIsLaterThanToday(LocalDate date) {
    if (date.compareTo(LocalDate.now()) <= 0) {
      throw new PaymentServiceException(String.format("Expiration date is incorrect: %s", date));
    }
  }
}
