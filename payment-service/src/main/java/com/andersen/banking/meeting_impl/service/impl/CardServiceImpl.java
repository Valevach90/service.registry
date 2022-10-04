package com.andersen.banking.meeting_impl.service.impl;

import static com.andersen.banking.meeting_impl.util.CardGenerator.generateExpirationTime;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.meeting_impl.aop.LogAnnotation;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.CardProductService;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.util.CardGenerator;
import com.andersen.banking.meeting_impl.util.CryptWithSHA;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.management.openmbean.KeyAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CardService implementation.
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final CardProductService cardProductService;

    @Transactional(readOnly = true)
    @Override
    @LogAnnotation(before = true, after = true)
    public Card findById(UUID id) {
        return cardRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Card.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    @LogAnnotation(before = true, after = true)
    public Page<Card> findAll(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Transactional
    @Override
    @LogAnnotation(before = true, after = true)
    public Card update(Card card) {
        UUID accountId = card.getAccount().getId();
        findById(card.getId());
        card.setAccount(accountService.findById(card.getAccount().getId()));
        card.setCardProduct(cardProductService.findById(card.getCardProduct().getId()));

        setCryptFirstNums(card);
        return cardRepository.save(card);
    }

    @Transactional
    @Override
    @LogAnnotation(before = true, after = true)
    public Card deactivateById(UUID id) {
        Card card = findById(id);
        card.setActive(false);

        return cardRepository.save(card);
    }

    @Transactional
    @Override
    public boolean deactivateSomeExpiredCards() {
        List<Card> cardsToDeactivate = cardRepository.findCardsToDeactivate();

        if (!cardsToDeactivate.isEmpty()) {
            cardsToDeactivate.forEach(card -> {
                card.setActive(false);
            });
            cardRepository.saveAll(cardsToDeactivate);

            return true;
        } else {
            return false;
        }
    }

    @Retryable(value = KeyAlreadyExistsException.class, backoff = @Backoff(delay = 1000))
    @Transactional
    @Override
    @LogAnnotation(before = true, after = true)
    public Card create(Card card) {
        Account account = accountService.findById(card.getAccount().getId());
        CardProduct cardProduct = cardProductService.findById(card.getCardProduct().getId());

        String cardNumber = CardGenerator.generateCardNumber(cardProduct.getTypeCard().getPaymentSystem(), cardProduct.getTypeCard().getTypeName(),
                account.getCurrency(), cardRepository.count());

        setUpCardInfo(card, account, cardProduct, cardNumber);

        //check if card already exists
        if (cardRepository.existsByFirstTwelveNumbersAndLastFourNumbers(card.getFirstTwelveNumbers(), card.getLastFourNumbers())) {
            throw new KeyAlreadyExistsException("Card already exists");
        } else {
            Card savedCard = cardRepository.save(card);
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
    @LogAnnotation(before = true, after = true)
    public Page<Card> findByAccountId(UUID id, Pageable pageable) {
        return cardRepository.getCardByAccountId(id, pageable);
    }

    @Override
    @LogAnnotation(before = true, after = true)
    public Page<Card> findAllByTypeCard(String payment, String type, Pageable pageable) {
        return cardRepository.findCardByPaymentSystemAndType(payment, type, pageable);
    }

    private void setCryptFirstNums(Card card) {
        String firstTwelveNums = card.getFirstTwelveNumbers();
        card.setFirstTwelveNumbers(CryptWithSHA.getCrypt(firstTwelveNums));
    }

    @Transactional(readOnly = true)
    @Override
    @LogAnnotation(before = true, after = true)
    public Page<Card> findByOwnerId(UUID id, Pageable pageable) {
        return cardRepository.findCardByAccount_OwnerId(id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    @LogAnnotation(before = true, after = true)
    public Page<Card> findByOwnerIdExceptCard(UUID id, UUID cardId, Pageable pageable) {
        Card chosenCard =
                cardRepository
                        .findById(cardId)
                        .orElseThrow(() -> new NotFoundException(Card.class, cardId));

        UUID accountId = chosenCard.getAccount().getId();
        return cardRepository.findByAccount_OwnerIdAndAccount_IdNot(id, accountId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    @LogAnnotation(before = true, after = true)
    public Card findByNums(String twelveNums, String fourNums) {
        String hash = CryptWithSHA.getCrypt(twelveNums);
        Optional<Card> card =
                cardRepository.findByFirstTwelveNumbersAndLastFourNumbers(hash, fourNums);

        if (card.isPresent()) {
            return card.get();
        } else {
            throw new NotFoundException(Card.class);
        }
    }
}
