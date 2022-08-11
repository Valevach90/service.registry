package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.CardController;
import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import com.andersen.banking.service.payment.meeting_impl.exception.MapperException;
import com.andersen.banking.service.payment.meeting_impl.exception.NotFoundException;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.mapper.TypeCardMapper;
import com.andersen.banking.service.payment.meeting_impl.service.CardService;
import com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
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
  private CardUpdateDto cardUpdateDto;
  private CardRegistrationDto cardRegistrationDto;
  private CardResponseDto cardResponseDto;

  @BeforeEach
  void init() {
    card = CardUnitTestGenerator.populateCard();
    cardUpdateDto = CardUnitTestGenerator.populateCardUpdateDto();
    cardRegistrationDto = CardUnitTestGenerator.populateCardRegistrationDto();
    cardResponseDto = CardUnitTestGenerator.populateCardResponseDto();
  }

  @Autowired
  CardController cardController;

  @MockBean
  CardService cardService;
  @MockBean
  CardMapper cardMapper;
  @MockBean
  TypeCardMapper typeCardMapper;

  @Test
  void findById_ShouldReturnCardDto_WhenIdIsCorrect() {

    Mockito.when(cardService.findById(ID)).thenReturn(card);
    Mockito.when(cardMapper.toCardResponseDto(card)).thenReturn(cardResponseDto);
    Assertions.assertEquals(cardResponseDto, cardController.findById(ID));
  }

  @Test
  void findById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
    Mockito.when(cardService.findById(ID)).thenThrow(new NotFoundException(Card.class, ID));
    Assertions.assertThrows(NotFoundException.class, () -> cardController.findById(ID));
  }

  @Test
  void findAll_ShouldReturnPageOfCards() {
    List<CardResponseDto> cardUpdateDtos = generateListOfCardDto();
    List<Card> cards = generateCards();
    Pageable pageable = createPageable();
    Page<CardResponseDto> pageOfDto = new PageImpl<>(cardUpdateDtos, pageable, SIZE_PAGE);
    Page<Card> pageOfCards = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Mockito.when(cardService.findAll(pageable)).thenReturn(pageOfCards);
    Mockito.when(cardMapper.toCardResponseDto(Mockito.any(Card.class))).thenReturn(new CardResponseDto());

    Page<CardResponseDto> result = cardController.findAll(pageable);

    Assertions.assertEquals(pageOfDto, result);
  }

  @Test
  void updateCards_ShouldUpdateCard_WhenCardDtoISCorrect() {
    Mockito.when(cardMapper.toCard(cardUpdateDto)).thenReturn(card);
    Mockito.when(cardService.update(card)).thenReturn(card);
    Mockito.when(cardMapper.toCardResponseDto(card)).thenReturn(cardResponseDto);

    Assertions.assertEquals(cardResponseDto, cardController.updateCard(cardUpdateDto));
  }

  @Test
  void updateCard_ShouldThrowMapperException_WhenCardDtoISIncorrect() {
    Mockito.when(cardMapper.toCard(cardUpdateDto)).thenThrow(new MapperException());

    Assertions.assertThrows(MapperException.class, () -> cardController.updateCard(cardUpdateDto));
  }

  @Test
  void updateCard_ShouldThrowNotFoundException_WhenCardDtoISIncorrect() {
    Mockito.when(cardMapper.toCard(cardUpdateDto)).thenReturn(card);
    Mockito.when(cardService.update(card)).thenThrow(new NotFoundException(Card.class, card.getId()));

    Assertions.assertThrows(NotFoundException.class, () -> cardController.updateCard(cardUpdateDto));
  }

  @Test
  void deleteById_ShouldReturnDeletedObject_WhenIdIsCorrect() {
    Mockito.when(cardService.deleteById(ID)).thenReturn(card);
    Mockito.when(cardMapper.toCardResponseDto(card)).thenReturn(cardResponseDto);

    Assertions.assertEquals(cardResponseDto, cardController.deleteById(ID));
  }

  @Test
  void deleteById_ShouldThrowNotFoundException_WhenIdIsIncorrect() {
    Mockito.when(cardService.deleteById(ID)).thenThrow(new NotFoundException(Card.class, ID));

    Assertions.assertThrows(NotFoundException.class, () -> cardController.deleteById(ID));
  }

  @Test
  void create_ShouldReturnCardDto_WhenCardDtoIsCorrect() {
    Mockito.when(cardMapper.toCard(cardRegistrationDto)).thenReturn(card);
    Mockito.when(cardService.create(card)).thenReturn(card);
    Mockito.when(cardMapper.toCardResponseDto(card)).thenReturn(cardResponseDto);

    Assertions.assertEquals(cardResponseDto, cardController.create(cardRegistrationDto));
  }

  @Test
  void create_ShouldThrowMapperException_WhenCardDtoIsIncorrect() {
    Mockito.when(cardMapper.toCard(cardRegistrationDto)).thenThrow(new MapperException());

    Assertions.assertThrows(MapperException.class, () -> cardController.create(cardRegistrationDto));
  }

  @Test
  void findAllByTypeCard_ShouldReturnPageOfCards() {
    List<CardResponseDto> cardUpdateDtos = generateListOfCardDtoWithTypeCard();
    List<Card> cards = generateCardsWithNonDefaultTypeCard();
    Pageable pageable = createPageable();
    Page<CardResponseDto> pageOfDto = new PageImpl<>(cardUpdateDtos, pageable, SIZE_PAGE);
    Page<Card> pageOfCards = new PageImpl<>(cards, pageable, SIZE_PAGE);

    CardResponseDto cardResponseDto = new CardResponseDto();
    addTypeCardInDto(cardResponseDto);

    Mockito.when(cardService.findAll(pageable)).thenReturn(pageOfCards);
    Mockito.when(cardMapper.toCardResponseDto(Mockito.any(Card.class))).thenReturn(cardResponseDto);

    Page<CardResponseDto> result = cardController.findAll(pageable);

    Assertions.assertEquals(pageOfDto, result);
  }

  @Test
  void findAllByOwner_ShouldReturnPageOfCards() {
    List<CardResponseDto> cardUpdateDtos = generateListOfCardDto();
    List<Card> cards = generateCards();
    Pageable pageable = createPageable();
    Page<CardResponseDto> pageOfDto = new PageImpl<>(cardUpdateDtos, pageable, SIZE_PAGE);
    Page<Card> pageOfCards = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Mockito.when(cardService.findByOwnerId(1L, pageable)).thenReturn(pageOfCards);
    Mockito.when(cardMapper.toCardResponseDto(Mockito.any(Card.class))).thenReturn(new CardResponseDto());

    Page<CardResponseDto> result = cardController.findAllByOwner(1L, pageable);

    Assertions.assertEquals(pageOfDto, result);
  }

  @Test
  void findAllExceptChosenByAccountId_ShouldReturnPageOfCards() {
    List<CardResponseDto> cardUpdateDtos = generateListOfCardDto();
    List<Card> cards = generateCards();
    Pageable pageable = createPageable();
    Page<CardResponseDto> pageOfDto = new PageImpl<>(cardUpdateDtos, pageable, SIZE_PAGE);
    Page<Card> pageOfCards = new PageImpl<>(cards, pageable, SIZE_PAGE);

    Mockito.when(cardService.findByOwnerIdExceptCard(1L, 1L, pageable)).thenReturn(pageOfCards);
    Mockito.when(cardMapper.toCardResponseDto(Mockito.any(Card.class))).thenReturn(new CardResponseDto());

    Page<CardResponseDto> result = cardController.findAllExceptChosenByOwnerId(1L, 1L, pageable);

    Assertions.assertEquals(pageOfDto, result);
  }

  private List<CardResponseDto> generateListOfCardDto() {
    return Stream
            .generate(CardResponseDto::new)
            .limit(27)
            .collect(Collectors.toList());
  }

  private List<CardResponseDto> generateListOfCardDtoWithTypeCard() {

    return Stream
            .generate(CardResponseDto::new)
            .limit(27)
            .peek(this::addTypeCardInDto)
            .collect(Collectors.toList());
  }

  private void addTypeCardInDto(CardResponseDto c) {
    TypeCard typeCard = populateTypeCard(new TypeCard());

    c.setTypeName(typeCard.getTypeName());
    c.setPaymentSystem(typeCard.getPaymentSystem());
  }

  private List<Card> generateCards() {
    return Stream
            .generate(Card::new)
            .limit(27)
            .collect(Collectors.toList());
  }

  private List<Card> generateCardsWithNonDefaultTypeCard() {
    return Stream
            .generate(Card::new)
            .limit(27)
            .peek(c -> c.setTypeCard(populateTypeCard(new TypeCard())))
            .collect(Collectors.toList());
  }

  @NotNull
  private TypeCard populateTypeCard(TypeCard typeCard) {
    typeCard.setId(2L);
    typeCard.setPaymentSystem("MASTERCARD");
    typeCard.setTypeName("STANDARD");
    return typeCard;
  }

  private Pageable createPageable() {
    Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
    return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
  }
}
