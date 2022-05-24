package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.repositories.CardRepository;
import com.andersen.banking.service.payment.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.exceptions.PaymentServiceException;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;

  @Transactional(readOnly = true)
  @Override
  public Card findById(Long id) {
    log.debug("Find card by id: {}", id);

    return cardRepository.findById(id)
        .orElseThrow(() -> new PaymentServiceException(String.format("Card with id = %d not found.", id)));
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
  public void update(Card updatedCard) {
    log.debug("Trying to update card: {}", updatedCard);

    cardRepository.findById(updatedCard.getId())
        .orElseThrow(() -> new NotFoundException(Card.class, updatedCard.getId()));

    cardRepository.save(updatedCard);

    log.debug("Return updated card: {}", updatedCard);
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    log.info("Deleting card with id: {}", id);

    Card card = cardRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Card.class, id));

    cardRepository.deleteById(id);

    log.info("Deleted card: {} with id: {}", card, id);
  }

  @Transactional
  @Override
  public Card create(Card card) {
    log.info("Creating card: {}", card);

    card.setId(null);
    Card savedCard = cardRepository.save(card);

    log.info("Created card: {}", savedCard);
    return savedCard;
  }
}
