package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.repository.CardRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import com.andersen.banking.service.payment.meeting_impl.util.CryptWithSHA;
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
  private final AccountService accountService;

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

    Long accountId = card.getAccount().getId();
    findById(card.getId());
    card.setAccount(accountService.findById(accountId));

    String firstTwelveNums = card.getFirstTwelveNumbers();
    card.setFirstTwelveNumbers(CryptWithSHA.getCrypt(firstTwelveNums));

    Card updatedCard = cardRepository.save(card);

    log.debug("Return updated card: {}", updatedCard);

    return updatedCard;
  }

  @Transactional
  @Override
  public Card deleteById(Long id) {
    log.info("Trying to delete card with id: {}", id);

    Card card = findById(id);

    cardRepository.deleteById(id);

    log.info("Deleted card with id: {}", id);
    return card;
  }

  @Transactional
  @Override
  public Card create(Card card) {
    log.info("Creating card: {}", card);

    Account account = accountService.findById(card.getAccount().getId());
    card.setAccount(account);

    String first_twelve_nums = card.getFirstTwelveNumbers();
    card.setFirstTwelveNumbers(CryptWithSHA.getCrypt(first_twelve_nums));

    Card savedCard = cardRepository.save(card);

    log.info("Created card: {}", savedCard);
    return savedCard;
  }

  @Override
  public Page<Card> findByAccountId(Long id, Pageable pageable) {
    log.info("Find all cards by account_id: {}", id);

    Page<Card> cards = cardRepository.getCardByAccountId(id, pageable);

    log.info("Found {} cards", cards.getContent().size());
    return cards;
  }
}
