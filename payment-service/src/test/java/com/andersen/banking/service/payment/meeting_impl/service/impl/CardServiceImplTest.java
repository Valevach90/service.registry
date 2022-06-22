package com.andersen.banking.service.payment.meeting_impl.service.impl;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.repository.CardRepository;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.service.AccountService;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import java.time.LocalDate;
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
  AccountService accountService;

  @BeforeEach
  void initArguments() {
    returnedCard = new Card();
    receivedCard = new Card();
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
    receivedCard = populateCard(receivedCard);
    returnedCard = populateCard(returnedCard);

    Mockito.when(accountService.findById(receivedCard.getAccount().getId())).thenReturn(populateAccount(new Account()));
    Mockito.when(cardRepository.findById(receivedCard.getId())).thenReturn(Optional.of(receivedCard));
    Mockito.when(cardRepository.save(receivedCard)).thenReturn(returnedCard);

    Assertions.assertEquals(returnedCard, cardService.update(receivedCard));
  }

  @Test
  void update_ShouldThrowNotFoundException_WhenReceivedCardHasIncorrectId() {
    receivedCard = populateCard(receivedCard);

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
    receivedCard = populateCard(receivedCard);
    returnedCard = populateCard(returnedCard);

    Mockito.when(accountService.findById(5L)).thenReturn(populateAccount(new Account()));
    Mockito.when(cardRepository.save(receivedCard)).thenReturn(returnedCard);

    Assertions.assertEquals(returnedCard, cardService.create(receivedCard));
  }


  private List<Card> generateCards() {
    return Stream
        .generate(Card::new)
        .limit(27)
        .collect(Collectors.toList());
  }

  private Pageable createPageable() {
    Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
    return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
  }

  private Card populateCard(Card card) {
    card.setId(17L);
    card.setAccount(populateAccount(new Account()));
    card.setValidFromDate(LocalDate.of(2021, 10, 23));
    card.setExpireDate(LocalDate.of(2024, 10, 23));
    card.setFirstTwelveNumbers("123456789012");
    card.setLastFourNumbers("4567");
    card.setHolderName("Ivanov Ivan Ivanovich");
    return card;
  }

  private Account populateAccount(Account account) {
    account.setId(5L);
    account.setAccountNumber("1234567890");
    return account;
  }
}
