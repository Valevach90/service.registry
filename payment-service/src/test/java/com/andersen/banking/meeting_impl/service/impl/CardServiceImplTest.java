package com.andersen.banking.meeting_impl.service.impl;

import static com.andersen.banking.meeting_test.generators.CardUnitTestGenerator.populateCard;
import static com.andersen.banking.meeting_test.generators.CardUnitTestGenerator.populateTypeCard;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.TypeCard;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.CardService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest(classes = CardServiceImpl.class)
public class CardServiceImplTest {

    private static final UUID ID = UUID.randomUUID();

    private static final UUID ACCOUNT_ID = UUID.randomUUID();

    private static final UUID OWNER_ID = UUID.randomUUID();
    private static final UUID TYPE_CARD_ID = UUID.randomUUID();
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "accountId";
    private final TypeCard returnedTypeCard = populateTypeCard();
    private final TypeCard receivedTypeCard = populateTypeCard();
    @SpyBean CardService cardService;
    @MockBean CardRepository cardRepository;
    @MockBean TypeCardRepository typeCardRepository;
    @MockBean AccountService accountService;
    private Card returnedCard;
    private Card receivedCard;

    @BeforeEach
    void initArguments() {
        returnedCard = populateCard();
        receivedCard = populateCard();
    }

/*
                    !Need create typeCardServiceImpl Test
    @Disabled
    void update_ShouldReturnTypeCardResponseDto_WhenReceivedTypeCardIsCorrect() {
        Mockito.when(typeCardRepository.findById(receivedTypeCard.getId()))
                .thenReturn(Optional.of(receivedTypeCard));
        Mockito.when(typeCardRepository.save(receivedTypeCard)).thenReturn(returnedTypeCard);
        var result = cardService.updateTypeCard(receivedTypeCard);
        Assertions.assertEquals(returnedTypeCard, result);
    }

    @Disabled
    void update_ShouldThrowNotFoundException_WhenReceivedTypeCardHasIncorrectId() {
        Mockito.when(typeCardRepository.findById(receivedTypeCard.getId()))
                .thenThrow(new NotFoundException(Card.class, receivedTypeCard.getId()));
        Assertions.assertThrows(
                NotFoundException.class, () -> cardService.updateTypeCard(receivedTypeCard));
    }

    @Disabled
    void getTypeCard_ShouldReturnTypeCardResponseDto_WhenIdIsCorrect() {
        Mockito.when(typeCardRepository.findById(TYPE_CARD_ID))
                .thenReturn(Optional.of(returnedTypeCard));
        var result = cardService.getTypeCard(TYPE_CARD_ID);
        Assertions.assertEquals(returnedTypeCard, result);
    }

    @Disabled
    void getTypeCard_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        Assertions.assertThrows(
                NotFoundException.class, () -> cardService.getTypeCard(TYPE_CARD_ID));
    }

*/
    @Test
    void findById_ShouldReturnCard_WhenIdIsCorrect() {
        Mockito.when(cardRepository.findById(ID)).thenReturn(Optional.of(returnedCard));
        Assertions.assertEquals(returnedCard, cardService.findById(ID));
    }

    @Test
    void findById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        Assertions.assertThrows(NotFoundException.class, () -> cardService.findById(ID));
    }

    @Test
    void findAll_ShouldReturnPageOfCards() {
        List<Card> cards = generateCards();
        Pageable pageable = createPageable();
        Page<Card> page = new PageImpl<>(cards, pageable, SIZE_PAGE);

        Mockito.when(cardRepository.findAll(pageable)).thenReturn(page);

        Page<Card> result = cardService.findAll(pageable);

        Assertions.assertEquals(page, result);
    }

    @Test
    void update_ShouldReturnCard_WhenReceivedCardIsCorrect() {
        Mockito.when(accountService.findById(receivedCard.getAccount().getId()))
                .thenReturn(populateAccount(new Account()));
        Mockito.when(cardRepository.findById(receivedCard.getId()))
                .thenReturn(Optional.of(receivedCard));
        Mockito.when(cardRepository.save(receivedCard)).thenReturn(returnedCard);

        TypeCard typeCard = returnedCard.getCardProduct().getTypeCard();
        Mockito.when(
                        typeCardRepository.findByPaymentSystemAndTypeName(
                                typeCard.getPaymentSystem(), typeCard.getTypeName()))
                .thenReturn(Optional.of(typeCard));

        Assertions.assertEquals(returnedCard, cardService.update(receivedCard));
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenReceivedCardHasIncorrectId() {
        Mockito.when(cardRepository.findById(receivedCard.getId()))
                .thenThrow(new NotFoundException(Card.class, receivedCard.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> cardService.update(receivedCard));
    }

    @Test
    void deleteById_ShouldReturnDeletedCard_WhenCardIdIsCorrect() {
        Mockito.when(cardRepository.findById(ID)).thenReturn(Optional.of(returnedCard));

        Assertions.assertEquals(returnedCard, cardService.deleteById(ID));
    }

    @Test
    void deleteById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
        Mockito.when(cardRepository.findById(ID))
                .thenThrow(new NotFoundException(Card.class, receivedCard.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> cardService.deleteById(ID));
    }

    @Test
    void create_ShouldReturnCard_WhenReceivedCardIsCorrect() {
        Mockito.when(accountService.findById(ACCOUNT_ID))
                .thenReturn(populateAccount(new Account()));
        Mockito.when(cardRepository.save(receivedCard)).thenReturn(returnedCard);

        TypeCard typeCard = returnedCard.getCardProduct().getTypeCard();
        Mockito.when(
                        typeCardRepository.findByPaymentSystemAndTypeName(
                                typeCard.getPaymentSystem(), typeCard.getTypeName()))
                .thenReturn(Optional.of(typeCard));

        Assertions.assertEquals(returnedCard, cardService.create(receivedCard));
    }

    @Test
    void findAllByTypeCard_ShouldReturnPageOfCards() {
        String checkPayment = "MASTERCARD";
        String checkType = "STANDARD";

        List<Card> filtered =
                generateCardsWithTypeCard().stream()
                        .filter(c -> c.getCardProduct().getTypeCard().getTypeName().equals(checkType))
                        .filter(c -> c.getCardProduct().getTypeCard().getPaymentSystem().equals(checkPayment))
                        .collect(Collectors.toList());

        Pageable pageable = createPageable();
        Page<Card> page = new PageImpl<>(filtered, pageable, SIZE_PAGE);

        Mockito.when(
                        cardRepository.findCardByPaymentSystemAndType(
                                checkPayment, checkType, pageable))
                .thenReturn(page);

        Page<Card> result = cardService.findAllByTypeCard(checkPayment, checkType, pageable);

        Assertions.assertEquals(page, result);
    }

    @Test
    void findAllByTypeCardAndOnlyType_ShouldReturnPageOfCards() {
        String checkType = "STANDARD";

        List<Card> filtered =
                generateCardsWithTypeCard().stream()
                        .filter(c -> c.getCardProduct().getTypeCard().getTypeName().equals(checkType))
                        .collect(Collectors.toList());

        Pageable pageable = createPageable();
        Page<Card> page = new PageImpl<>(filtered, pageable, SIZE_PAGE);

        Mockito.when(cardRepository.findCardByPaymentSystemAndType(null, checkType, pageable))
                .thenReturn(page);

        Page<Card> result = cardService.findAllByTypeCard(null, checkType, pageable);
        Assertions.assertEquals(page, result);
    }

    @Test
    void findByOwnerId_ShouldReturnPageOfCards() {
        List<Card> cards = generateCards();
        Pageable pageable = createPageable();
        Page<Card> page = new PageImpl<>(cards, pageable, SIZE_PAGE);

        Mockito.when(cardRepository.findCardByAccount_OwnerId(OWNER_ID, pageable)).thenReturn(page);

        Page<Card> result = cardService.findByOwnerId(OWNER_ID, pageable);

        Assertions.assertEquals(page, result);
    }

    @Test
    void findByOwnerIdExceptCard_ShouldReturnPageOfCards() {
        List<Card> cards = generateCards();
        Pageable pageable = createPageable();
        Page<Card> page = new PageImpl<>(cards, pageable, SIZE_PAGE);

        Account account = new Account();
        account.setId(ACCOUNT_ID);

        Card card = new Card();
        card.setId(ID);
        card.setAccount(account);

        Mockito.when(cardRepository.findById(ID)).thenReturn(Optional.of(card));
        Mockito.when(
                        cardRepository.findByAccount_OwnerIdAndAccount_IdNot(
                                OWNER_ID, ACCOUNT_ID, pageable))
                .thenReturn(page);

        Page<Card> result = cardService.findByOwnerIdExceptCard(OWNER_ID, ID, pageable);

        Assertions.assertEquals(page, result);
    }

    @Test
    void findByOwnerIdExceptCard_ShouldThrowNotFoundException_WhenCardIdIsIncorrect() {
        Pageable pageable = createPageable();

        Mockito.when(cardRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> cardService.findByOwnerIdExceptCard(OWNER_ID, ID, pageable));
    }

    private List<Card> generateCards() {
        return Stream.generate(Card::new).limit(27).collect(Collectors.toList());
    }

    private List<Card> generateCardsWithTypeCard() {
        List<Card> collect =
                Stream.generate(Card::new)
                        .limit(5)
                        .peek(c -> c.getCardProduct().setTypeCard(createTypeCard("MASTERCARDS")))
                        .collect(Collectors.toList());
        List<Card> addOtherType =
                Stream.generate(Card::new)
                        .limit(5)
                        .peek(c -> c.getCardProduct().setTypeCard(createTypeCard("VISAS")))
                        .toList();
        collect.addAll(addOtherType);
        return collect;
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }

    private Account populateAccount(Account account) {
        account.setId(ACCOUNT_ID);
        account.setAccountNumber("1234567890");
        return account;
    }

    private TypeCard createTypeCard(String type) {
        TypeCard typeCard = new TypeCard();
        switch (type) {
            case "VISAS":
                {
                    typeCard.setId(UUID.randomUUID());
                    typeCard.setPaymentSystem("VISA");
                    typeCard.setTypeName("STANDARD");
                }
            case "MASTERCARDS":
                {
                    typeCard.setId(UUID.randomUUID());
                    typeCard.setPaymentSystem("MASTERCARD");
                    typeCard.setTypeName("STANDARD");
                }
        }
        return typeCard;
    }
}
