package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.CardController;
import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.exceptions.MapperException;
import com.andersen.banking.service.payment.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.mapping.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.mapping.CardRegistrationDtoMapper;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest(classes = CardControllerImpl.class)
public class CardControllerImplTest {

  private static final Long ID = 17L;
  private static final Integer NUMBER_PAGE = 0;
  private static final Integer SIZE_PAGE = 10;
  private static final String SORT_FIELD = "accountId";

  private Card card;
  private CardDto cardDto;
  private CardRegistrationDto cardRegistrationDto;

  @BeforeEach
  void init() {
    card = new Card();
    cardDto = new CardDto();
    cardRegistrationDto = new CardRegistrationDto();
  }

  @Autowired
  CardController cardController;

  @MockBean
  CardService cardService;
  @MockBean
  CardMapper cardMapper;
  @MockBean
  CardRegistrationDtoMapper cardRegistrationDtoMapper;

  @Test
  void findById_ShouldReturnCardDto_WhenIdISCorrect() {

    Mockito.when(cardService.findById(ID)).thenReturn(card);
    Mockito.when(cardMapper.toCardDto(card)).thenReturn(cardDto);
    Assertions.assertEquals(cardDto, cardController.findById(ID));
  }

  @Test
  void findById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
    Mockito.when(cardService.findById(ID)).thenThrow(new NotFoundException(Card.class, ID));
    Assertions.assertThrows(NotFoundException.class, () -> cardController.findById(ID));
  }

  @Test
  void findAll_ShouldReturnPageOfCards() {
    List<CardDto> cardDtos = generateListOfCardDto();
    List<Card> cards = generateCards();
    Pageable pageable = createPageable();
    Page<CardDto> pageOfDto = new PageImpl<>(cardDtos, pageable, SIZE_PAGE);
    Page<Card> pageOfCards = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Mockito.when(cardService.findAll(pageable)).thenReturn(pageOfCards);
    Mockito.when(cardMapper.toCardDto(Mockito.any(Card.class))).thenReturn(new CardDto());

    Page<CardDto> result = cardController.findAll(pageable);

    Assertions.assertEquals(pageOfDto, result);
  }

  @Test
  void updateCards_ShouldUpdateCard_WhenCardDtoISCorrect() {
    card = populateCard();
    cardDto = populateCardDto();

    Mockito.when(cardMapper.toCard(cardDto)).thenReturn(card);
    Mockito.when(cardService.update(card)).thenReturn(card);
    Mockito.when(cardMapper.toCardDto(card)).thenReturn(cardDto);

    Assertions.assertEquals(cardDto, cardController.updateCard(cardDto));
  }

  @Test
  void updateCard_ShouldThrowMapperException_WhenCardDtoISIncorrect() {
    cardDto = populateCardDto();

    Mockito.when(cardMapper.toCard(cardDto)).thenThrow(new MapperException());

    Assertions.assertThrows(MapperException.class, () -> cardController.updateCard(cardDto));
  }

  @Test
  void updateCard_ShouldThrowNotFoundException_WhenCardDtoISIncorrect() {
    cardDto = populateCardDto();
    card = populateCard();

    Mockito.when(cardMapper.toCard(cardDto)).thenReturn(card);
    Mockito.when(cardService.update(card)).thenThrow(new NotFoundException(Card.class, card.getId()));

    Assertions.assertThrows(NotFoundException.class, () -> cardController.updateCard(cardDto));
  }

  @Test
  void deleteById_ShouldReturnDeletedObject_WhenIdIsCorrect() {
    Mockito.when(cardService.deleteById(ID)).thenReturn(card);
    Mockito.when(cardMapper.toCardDto(card)).thenReturn(cardDto);

    Assertions.assertEquals(cardDto, cardController.deleteById(ID));
  }

  @Test
  void deleteById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
    Mockito.when(cardService.deleteById(ID)).thenThrow(new NotFoundException(Card.class, ID));

    Assertions.assertThrows(NotFoundException.class, () -> cardController.deleteById(ID));
  }

  @Test
  void create_ShouldReturnCardDto_WhenCardRegistrationDtoIsCorrect() {
    cardRegistrationDto = populateCardRegistrationDto();
    card = populateCard();
    cardDto = populateCardDto();

    Mockito.when(cardRegistrationDtoMapper.toCard(cardRegistrationDto)).thenReturn(card);
    Mockito.when(cardService.create(card)).thenReturn(card);
    Mockito.when(cardMapper.toCardDto(card)).thenReturn(cardDto);

    Assertions.assertEquals(cardDto, cardController.create(cardRegistrationDto));
  }

  @Test
  void create_ShouldThrowMapperException_WhenCardRegistrationDtoIsIncorrect() {
    cardRegistrationDto = populateCardRegistrationDto();
    Mockito.when(cardRegistrationDtoMapper.toCard(cardRegistrationDto)).thenThrow(new MapperException());

    Assertions.assertThrows(MapperException.class, () -> cardController.create(cardRegistrationDto));
  }

  private Card populateCard() {
    Account account = new Account();
    account.setId(5L);

    Card card = new Card();
    card.setId(17L);
    card.setAccount(account);
    card.setCardNumber("1234567890123456");
    card.setPinCode("1234");
    card.setExpirationDate(LocalDate.parse("2099-01-01"));
    card.setHolderName("Ivanov Ivan Ivanovich");
    return card;
  }

  private CardDto populateCardDto() {
    CardDto cardDto = new CardDto();
    cardDto.setId(17L);
    cardDto.setAccountId(5L);
    cardDto.setCardNumber("1234567890123456");
    cardDto.setPinCode("1234");
    cardDto.setExpirationDate(LocalDate.parse("2099-01-01"));
    cardDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardDto;
  }

  private CardRegistrationDto populateCardRegistrationDto() {
    CardRegistrationDto cardDto = new CardRegistrationDto();

    cardDto.setAccountId(5L);
    cardDto.setCardNumber("1234567890123456");
    cardDto.setPinCode("1234");
    cardDto.setExpirationDate(LocalDate.parse("2099-01-01"));
    cardDto.setHolderName("Ivanov Ivan Ivanovich");
    return cardDto;
  }

  private List<CardDto> generateListOfCardDto() {
    return Stream
        .generate(CardDto::new)
        .limit(27)
        .collect(Collectors.toList());
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
}
