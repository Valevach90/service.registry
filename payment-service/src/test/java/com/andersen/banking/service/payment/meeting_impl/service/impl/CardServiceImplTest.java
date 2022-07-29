package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import com.andersen.banking.service.payment.meeting_db.repository.CardRepository;
import com.andersen.banking.service.payment.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

  private static final Long ID = 17L;
  private static final Integer NUMBER_PAGE = 0;
  private static final Integer SIZE_PAGE = 10;
  private static final String SORT_FIELD = "accountId";

  private Card returnedCard;
  private Card receivedCard;

  @SpyBean
  CardService cardService;

  @MockBean
  CardRepository cardRepository;

  @MockBean
  TypeCardRepository typeCardRepository;

  @MockBean
  AccountService accountService;

  @BeforeEach
  void initArguments() {
    returnedCard = CardUnitTestGenerator.populateCard();
    receivedCard = CardUnitTestGenerator.populateCard();
  }

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
    Mockito.when(accountService.findById(receivedCard.getAccount().getId())).thenReturn(populateAccount(new Account()));
    Mockito.when(cardRepository.findById(receivedCard.getId())).thenReturn(Optional.of(receivedCard));
    Mockito.when(cardRepository.save(receivedCard)).thenReturn(returnedCard);

    TypeCard typeCard = returnedCard.getTypeCard();
    Mockito
            .when(typeCardRepository.findByPaymentSystemAndTypeName(typeCard.getPaymentSystem(), typeCard.getTypeName()))
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
    Mockito.when(cardRepository.findById(ID)).thenThrow(new NotFoundException(Card.class, receivedCard.getId()));

    Assertions.assertThrows(NotFoundException.class, () -> cardService.deleteById(ID));
  }

  @Test
  void create_ShouldReturnCard_WhenReceivedCardIsCorrect() {
    Mockito.when(accountService.findById(5L)).thenReturn(populateAccount(new Account()));
    Mockito.when(cardRepository.save(receivedCard)).thenReturn(returnedCard);

    TypeCard typeCard = returnedCard.getTypeCard();
    Mockito
            .when(typeCardRepository.findByPaymentSystemAndTypeName(typeCard.getPaymentSystem(), typeCard.getTypeName()))
            .thenReturn(Optional.of(typeCard));

    Assertions.assertEquals(returnedCard, cardService.create(receivedCard));
  }

  @Test
  void findAllByTypeCard_ShouldReturnPageOfCards() {
    String checkPayment = "MASTERCARD";
    String checkType = "STANDARD";

    List<Card> masterCards = generateCardsWithNonDefaultTypeCard();

    Pageable pageable = createPageable();
    Page<Card> page = new PageImpl<>(masterCards, pageable, SIZE_PAGE);

    Mockito.when(cardRepository.nat(checkPayment, checkType, pageable)).thenReturn(page);

    Page<Card> result = cardService.findAllByTypeCard(checkPayment, checkType, pageable);

    Assertions.assertEquals(page, result);
  }

  @Test
  void findAllByTypeCardAndOnlyType_ShouldReturnPageOfCards() {
    String checkType = "STANDARD";

    List<Card> cards = generateCards();
    cards.addAll(generateCardsWithNonDefaultTypeCard());
    Pageable pageable = createPageable();
    Page<Card> page = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Mockito.when(cardRepository.nat(null, checkType, pageable)).thenReturn(page);

    Page<Card> result = cardService.findAllByTypeCard(null, checkType, pageable);
    Assertions.assertEquals(page, result);
  }

  private List<Card> generateCards() {
    return Stream
            .generate(Card::new)
            .limit(27)
            .collect(Collectors.toList());
  }

  private List<Card> generateCardsWithNonDefaultTypeCard() {
    TypeCard typeCard = new TypeCard();
    typeCard.setId(2L);
    typeCard.setPaymentSystem("MASTERCARD");
    typeCard.setTypeName("STANDARD");

    return Stream
            .generate(Card::new)
            .limit(27)
            .peek(c -> c.setTypeCard(typeCard))
            .collect(Collectors.toList());
  }

  private Pageable createPageable() {
    Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
    return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
  }

  private Account populateAccount(Account account) {
    account.setId(5L);
    account.setAccountNumber("1234567890");
    return account;
  }
}
