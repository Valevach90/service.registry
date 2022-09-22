package com.andersen.banking.meeting_impl.service.impl;

import static com.andersen.banking.meeting_impl.util.CardGenerator.generateExpirationTime;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.CardProductService;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.util.CardGenerator;
import com.andersen.banking.meeting_impl.util.CryptWithSHA;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import javax.management.openmbean.KeyAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** CardService implementation. */
@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final CardProductService cardProductService;

    @Transactional(readOnly = true)
    @Override
    public Card findById(UUID id) {
        log.info("Find card by id: {}", id);

        Card card =
                cardRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException(Card.class, id));

        log.info("Card with id {} successfully found", id);
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
        log.info("Trying to update card: {}", card);

        findById(card.getId());
        card.setAccount(accountService.findById(card.getAccount().getId()));
        card.setCardProduct(cardProductService.findById(card.getCardProduct().getId()));

        setCryptFirstNums(card);

        Card updatedCard = cardRepository.save(card);

        log.info("Return updated card: {}", updatedCard);

        return updatedCard;
    }

    @Transactional
    @Override
    public Card deleteById(UUID id) {
        log.info("Trying to deactivate card with id: {}", id);

        Card card = findById(id);
        card.setActive(false);

        cardRepository.save(card);

        log.info("Deactivated card with id: {}", id);
        return card;
    }

    @Retryable(value = KeyAlreadyExistsException.class, backoff = @Backoff(delay = 1000))
    @Transactional
    @Override
    public Card create(Card card) {
        log.info("Creating card with card product id: {}", card.getCardProduct().getId());

        Account account = accountService.findById(card.getAccount().getId());
        CardProduct cardProduct = cardProductService.findById(card.getCardProduct().getId());

        String cardNumber = CardGenerator.generateCardNumber(cardProduct.getTypeCard().getPaymentSystem(), cardProduct.getTypeCard().getTypeName(),
                account.getCurrency(), cardRepository.count());
        log.info("card number was generated: {}", cardNumber);

        setUpCardInfo(card, account, cardProduct, cardNumber);

        //check if card already exists
        if (cardRepository.existsByFirstTwelveNumbersAndLastFourNumbers(card.getFirstTwelveNumbers(), card.getLastFourNumbers())) {
            log.error("Card {} already exists", cardNumber);
            throw new KeyAlreadyExistsException("Card already exists");
        } else {
            Card savedCard = cardRepository.save(card);
            log.info("Created card: {}", savedCard);
            return savedCard;
        }
    }

    private void setUpCardInfo(Card card, Account account, CardProduct cardProduct, String cardNumber){
        card.setAccount(account);
        card.setCardProduct(cardProduct);
        card.setValidFromDate(LocalDate.now());
        generateExpirationTime(card);
        card.setFirstTwelveNumbers(cardNumber.substring(0, 12));
        card.setLastFourNumbers(cardNumber.substring(12, 16));
        card.setActive(true);

        setCryptFirstNums(card);
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
        Optional<Card> card =
                cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(hash, fourNums);

        if (card.isPresent()) {
            log.info("Found card with number ***{}", fourNums);
            return card.get();
        } else {
            log.info("Not found card with number ***{}", fourNums);
            throw new NotFoundException(Card.class);
        }
    }
}
