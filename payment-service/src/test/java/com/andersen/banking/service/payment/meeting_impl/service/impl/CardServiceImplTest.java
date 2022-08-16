package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import com.andersen.banking.service.payment.meeting_db.repository.CardRepository;
import com.andersen.banking.service.payment.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.mapper.TypeCardMapper;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
  private static final Long TYPE_CARD_ID = 1L;
  private static final Integer NUMBER_PAGE = 0;
  private static final Integer SIZE_PAGE = 10;
  private static final String SORT_FIELD = "accountId";

  private Card returnedCard;
  private Card receivedCard;
  private TypeCard returnedTypeCard;
  private TypeCard receivedTypeCard;

  @SpyBean
  CardService cardService;

  @MockBean
  CardRepository cardRepository;

  @MockBean
  TypeCardRepository typeCardRepository;

  @Autowired
  TypeCardMapper typeCardMapper;

  @MockBean
  AccountService accountService;

  @BeforeEach
  void initArguments() {
    returnedCard = CardUnitTestGenerator.populateCard();
    receivedCard = CardUnitTestGenerator.populateCard();
    returnedTypeCard = CardUnitTestGenerator.populateTypeCard();
    receivedTypeCard = CardUnitTestGenerator.populateTypeCard();
  }

  @Test
  void update_ShouldReturnTypeCardResponseDto_WhenReceivedTypeCardIsCorrect() {
    Mockito.when(typeCardRepository.findById(receivedTypeCard.getId())).thenReturn(Optional.of(receivedTypeCard));
    Mockito.when(typeCardRepository.save(receivedTypeCard)).thenReturn(returnedTypeCard);
    var result = typeCardMapper.typeCardResponseDto2TypeCard(cardService
            .updateTypeCard(typeCardMapper.typeCard2TypeCardUpdateDto(receivedTypeCard)));
    Assertions.assertEquals(returnedTypeCard, result);
  }

  @Test
  void update_ShouldThrowNotFoundException_WhenReceivedTypeCardHasIncorrectId () {
    Mockito
            .when(typeCardRepository.findById(receivedTypeCard.getId()))
            .thenThrow(new NotFoundException(Card.class, receivedTypeCard.getId()));
    Assertions.assertThrows(NotFoundException.class,
            () -> cardService.updateTypeCard(typeCardMapper.typeCard2TypeCardUpdateDto(receivedTypeCard)));
  }

  @Test
  void getTypeCard_ShouldReturnTypeCardResponseDto_WhenIdIsCorrect() {
    Mockito.when(typeCardRepository.findById(TYPE_CARD_ID)).thenReturn(Optional.of(returnedTypeCard));
    var result = typeCardMapper
            .typeCardResponseDto2TypeCard(cardService.getTypeCard(TYPE_CARD_ID));
    Assertions.assertEquals(returnedTypeCard, result);
  }

  @Test
  void getTypeCard_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
    Assertions.assertThrows(NotFoundException.class, () -> cardService.getTypeCard(TYPE_CARD_ID));
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

    List<Card> filtered = generateCardsWithTypeCard()
            .stream()
            .filter(c -> c.getTypeCard().getTypeName().equals(checkType))
            .filter(c -> c.getTypeCard().getPaymentSystem().equals(checkPayment))
            .collect(Collectors.toList());

    Pageable pageable = createPageable();
    Page<Card> page = new PageImpl<>(filtered, pageable, SIZE_PAGE);

    Mockito.when(cardRepository.findCardByPaymentSystemAndType(checkPayment, checkType, pageable)).thenReturn(page);

    Page<Card> result = cardService.findAllByTypeCard(checkPayment, checkType, pageable);

    Assertions.assertEquals(page, result);
  }

  @Test
  void findAllByTypeCardAndOnlyType_ShouldReturnPageOfCards() {
    String checkType = "STANDARD";

    List<Card> filtered = generateCardsWithTypeCard()
            .stream()
            .filter(c -> c.getTypeCard().getTypeName().equals(checkType))
            .collect(Collectors.toList());

    Pageable pageable = createPageable();
    Page<Card> page = new PageImpl<>(filtered, pageable, SIZE_PAGE);

    Mockito.when(cardRepository.findCardByPaymentSystemAndType(null, checkType, pageable)).thenReturn(page);

    Page<Card> result = cardService.findAllByTypeCard(null, checkType, pageable);
    Assertions.assertEquals(page, result);
  }

  @Test
  void findByOwnerId_ShouldReturnPageOfCards() {
    List<Card> cards = generateCards();
    Pageable pageable = createPageable();
    Page<Card> page = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Mockito.when(cardRepository.findCardByAccount_OwnerId(3L, pageable)).thenReturn(page);

    Page<Card> result = cardService.findByOwnerId(3L, pageable);

    Assertions.assertEquals(page, result);
  }

  @Test
  void findByOwnerIdExceptCard_ShouldReturnPageOfCards() {
    List<Card> cards = generateCards();
    Pageable pageable = createPageable();
    Page<Card> page = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Account account = new Account();
    account.setId(1L);

    Card card = new Card();
    card.setId(1L);
    card.setAccount(account);

    Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
    Mockito.when(cardRepository.findByAccount_OwnerIdAndAccount_IdNot(1L, 1L, pageable)).thenReturn(page);

    Page<Card> result = cardService.findByOwnerIdExceptCard(1L, 1L, pageable);

    Assertions.assertEquals(page, result);
  }

  @Test
  void findByOwnerIdExceptCard_ShouldThrowNotFoundException_WhenCardIdIsIncorrect() {
    Pageable pageable = createPageable();

    Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> cardService.findByOwnerIdExceptCard(1L, 1L, pageable));
  }

  private List<Card> generateCards() {
    return Stream
            .generate(Card::new)
            .limit(27)
            .collect(Collectors.toList());
  }

  private List<Card> generateCardsWithTypeCard() {
    List<Card> collect = Stream
            .generate(Card::new)
            .limit(5)
            .peek(c -> c.setTypeCard(createTypeCard("MASTERCARDS")))
            .collect(Collectors.toList());
    List<Card> addOtherType = Stream
            .generate(Card::new)
            .limit(5)
            .peek(c -> c.setTypeCard(createTypeCard("VISAS")))
            .collect(Collectors.toList());
    collect.addAll(addOtherType);
    return collect;
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

  private TypeCard createTypeCard(String type) {
    TypeCard typeCard = new TypeCard();
    switch (type) {
      case "VISAS" : {
        typeCard.setId(1L);
        typeCard.setPaymentSystem("VISA");
        typeCard.setTypeName("STANDARD");
      }
      case "MASTERCARDS" : {
        typeCard.setId(4L);
        typeCard.setPaymentSystem("MASTERCARD");
        typeCard.setTypeName("STANDARD");
      }
    }
    return typeCard;
  }
}
