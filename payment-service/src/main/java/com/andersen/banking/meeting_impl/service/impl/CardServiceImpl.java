package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.TypeCard;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.util.CryptWithSHA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/** CardService implementation. */
@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;
  private final TypeCardRepository typeCardRepository;
  private final AccountService accountService;

  @Transactional(readOnly = true)
  @Override
  public Card findById(UUID id) {
    log.debug("Find card by id: {}", id);

    Card card =
        cardRepository.findById(id).orElseThrow(() -> new NotFoundException(Card.class, id));

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

    UUID accountId = card.getAccount().getId();
    findById(card.getId());
    card.setAccount(accountService.findById(accountId));

    setTypeCard(card);
    setCryptFirstNums(card);

    Card updatedCard = cardRepository.save(card);

    log.debug("Return updated card: {}", updatedCard);

    return updatedCard;
  }

  @Transactional
  @Override
  public Card deleteById(UUID id) {
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

    setTypeCard(card);
    setCryptFirstNums(card);

    Card savedCard = cardRepository.save(card);

    log.info("Created card: {}", savedCard);
    return savedCard;
  }

  @Override
  public Page<Card> findByAccountId(UUID id, Pageable pageable) {
    log.info("Find all cards by account_id: {}", id);

    Page<Card> cards = cardRepository.getCardByAccountId(id, pageable);

    log.info("Found {} cards", cards.getContent().size());
    return cards;
  }

  @Override
  public Page<Card> findAllByTypeCard(String payment, String type, Pageable pageable) {
    log.info("Find all cards with payment system {} and type {}", payment, type);

    Page<Card> cards = cardRepository.findCardByPaymentSystemAndType(payment, type, pageable);

    log.info("Found {} cards", cards.getContent().size());
    return cards;
  }

  @Override
  public TypeCard getTypeCard(UUID id) {
    log.debug("Get card type by id : {}", id);

    return findTypeCardById(id);
  }

  @Override
  public TypeCard updateTypeCard(TypeCard typeCard) {
    log.debug("Trying to update card type: {}", typeCard);

    TypeCard updatedTypeCard = findTypeCardById(typeCard.getId());
    updatedTypeCard.setTypeName(typeCard.getTypeName());
    updatedTypeCard.setPaymentSystem(typeCard.getPaymentSystem());
    typeCardRepository.save(updatedTypeCard);

    log.debug("Return update card type : {}", updatedTypeCard);

    return updatedTypeCard;
  }

  private TypeCard findTypeCardById(UUID id) {
    return typeCardRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(TypeCard.class, id));
  }

  private void setTypeCard(Card card) {
    TypeCard typeCard = card.getTypeCard();
    TypeCard existingTypeCard =
        typeCardRepository
            .findByPaymentSystemAndTypeName(typeCard.getPaymentSystem(), typeCard.getTypeName())
            .orElseThrow(() -> new NotFoundException(TypeCard.class, null));
    card.setTypeCard(existingTypeCard);
  }

  private void setCryptFirstNums(Card card) {
    String firstTwelveNums = card.getFirstTwelveNumbers();
    card.setFirstTwelveNumbers(CryptWithSHA.getCrypt(firstTwelveNums));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Card> findByOwnerId(UUID id, Pageable pageable) {
    log.info("Find all cards by owner: {}", id);

    Page<Card> cardsByOwner = cardRepository.findCardByAccount_OwnerId(id, pageable);

    log.info("Found {} cards", cardsByOwner.getTotalElements());

    return cardsByOwner;
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Card> findByOwnerIdExceptCard(UUID id, UUID cardId, Pageable pageable) {
    log.info("Find all cards by owner except already chosen card: {}", id);
    Card chosenCard =
        cardRepository
            .findById(cardId)
            .orElseThrow(() -> new NotFoundException(Card.class, cardId));

    UUID accountId = chosenCard.getAccount().getId();

    Page<Card> cardsSet =
        cardRepository.findByAccount_OwnerIdAndAccount_IdNot(id, accountId, pageable);

    log.info("Found {} cards", cardsSet.getTotalElements());

    return cardsSet;
  }

  @Override
  @Transactional(readOnly = true)
  public Card findByNums(String twelveNums, String fourNums) {
    log.info("Finding card by card's number: ***{}", fourNums);
    String hash = CryptWithSHA.getCrypt(twelveNums);
    Optional<Card> card = cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(hash, fourNums);

    if (card.isPresent()) {
      log.info("Found card with number ***{}", fourNums);
      return card.get();
    } else {
      log.info("Not found card with number ***{}", fourNums);
      throw new NotFoundException(Card.class);
    }
  }
}
