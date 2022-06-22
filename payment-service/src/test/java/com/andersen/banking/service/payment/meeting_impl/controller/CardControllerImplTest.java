package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.CardController;
import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.exception.MapperException;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapper;
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

  @BeforeEach
  void init() {
    card = new Card();
    cardDto = new CardDto();
  }

  @Autowired
  CardController cardController;

  @MockBean
  CardService cardService;
  @MockBean
  CardMapper cardMapper;

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
    card = populateCard(card);
    cardDto = populateCardDto(cardDto);

    Mockito.when(cardMapper.toCard(cardDto)).thenReturn(card);
    Mockito.when(cardService.update(card)).thenReturn(card);
    Mockito.when(cardMapper.toCardDto(card)).thenReturn(cardDto);

    Assertions.assertEquals(cardDto, cardController.updateCard(cardDto));
  }

  @Test
  void updateCard_ShouldThrowMapperException_WhenCardDtoISIncorrect() {
    cardDto = populateCardDto(cardDto);

    Mockito.when(cardMapper.toCard(cardDto)).thenThrow(new MapperException());

    Assertions.assertThrows(MapperException.class, () -> cardController.updateCard(cardDto));
  }

  @Test
  void updateCard_ShouldThrowNotFoundException_WhenCardDtoISIncorrect() {
    cardDto = populateCardDto(cardDto);
    card = populateCard(card);

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
  void create_ShouldReturnCardDto_WhenCardDtoIsCorrect() {

    card = populateCard(card);
    cardDto = populateCardDto(cardDto);

    Mockito.when(cardMapper.toCard(cardDto)).thenReturn(card);
    Mockito.when(cardService.create(card)).thenReturn(card);
    Mockito.when(cardMapper.toCardDto(card)).thenReturn(cardDto);

    Assertions.assertEquals(cardDto, cardController.create(cardDto));
  }

  @Test
  void create_ShouldThrowMapperException_WhenCardDtoIsIncorrect() {
    cardDto = populateCardDto(cardDto);
    Mockito.when(cardMapper.toCard(cardDto)).thenThrow(new MapperException());

    Assertions.assertThrows(MapperException.class, () -> cardController.create(cardDto));
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

  private CardDto populateCardDto(CardDto cardDto) {
    cardDto.setId(17L);
    cardDto.setAccountId(5L);
    cardDto.setFirstTwelveNumbers("123456789012");
    cardDto.setLastFourNumbers("4567");
    cardDto.setValidFromDate(LocalDate.of(2021, 10, 23));
    cardDto.setExpireDate(LocalDate.of(2024, 10, 23));
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
