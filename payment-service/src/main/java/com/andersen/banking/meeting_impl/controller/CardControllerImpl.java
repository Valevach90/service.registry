package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.CardController;
import com.andersen.banking.meeting_api.dto.*;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.TypeCard;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.feign.RegistrationClient;
import com.andersen.banking.meeting_impl.mapper.CardMapper;
import com.andersen.banking.meeting_impl.mapper.TypeCardMapper;
import com.andersen.banking.meeting_impl.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

/**
 * Implementation class for CardController.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerImpl implements CardController {

  private final CardService cardService;
  private final CardMapper cardMapper;
  private final TypeCardMapper typeCardMapper;

  @Autowired RegistrationClient registrationClient;

  /**
   * End-point to find Card entity by ud.
   *
   * @param id
   * @return
   */
  @Override
  public CardResponseDto findById(@PathVariable UUID id) {
    log.trace("Receiving card id: {}", id);

    CardResponseDto cardResponseDto = cardMapper.toCardResponseDto(cardService.findById(id));

    log.trace("Returning card with id: {}", id);
    return cardResponseDto;
  }

  /**
   * End-point to find all Card entities.
   *
   * @param pageable
   * @return
   */
  @Override
  public Page<CardResponseDto> findAll(Pageable pageable) {
    log.trace("Receiving request for all cards");

    Page<CardResponseDto> result = cardService.findAll(pageable).map(cardMapper::toCardResponseDto);

    log.trace("Returning list of cards: {}", result.getContent());
    return result;
  }

  @Override
  public Page<CardResponseDto> findAllByAccountId(UUID id, Pageable pageable) {
    log.trace("Receiving request for getting all cards by account id");

    Page<CardResponseDto> result =
        cardService.findByAccountId(id, pageable).map(cardMapper::toCardResponseDto);

    log.trace("Returning page of cards: {}", result.getContent());
    return result;
  }

  /**
   * End-point to update existing Card.
   *
   * @param cardUpdateDto
   * @return
   */
  @Override
  public CardResponseDto updateCard(CardUpdateDto cardUpdateDto) {
    log.trace("Receiving card: {}", cardUpdateDto);

    Card cardToUpdate = cardMapper.toCard(cardUpdateDto);
    CardResponseDto cardResponseDto =
        cardMapper.toCardResponseDto(cardService.update(cardToUpdate));

    log.trace("Returning updated card: {}", cardResponseDto);
    return cardResponseDto;
  }

  /**
   * End-point to delete Card.
   *
   * @param id
   * @return
   */
  @Override
  public CardResponseDto deleteById(UUID id) {
    log.trace("Receiving card id {}", id);

    Card deletedCard = cardService.deleteById(id);

    log.trace("Returning deleted card with id: {}", id);
    return cardMapper.toCardResponseDto(deletedCard);
  }

  /**
   * End-point to register new Card.
   *
   * @param cardDto
   * @return
   */
  @Override
  public CardResponseDto create(CardRegistrationDto cardDto) {
    log.trace("Receiving request for creating card card: {}", cardDto);

    Card cardToCreate = cardMapper.toCard(cardDto);

    CardResponseDto cardResponseDto =
        cardMapper.toCardResponseDto(cardService.create(cardToCreate));

    log.trace("Returning created card: {}", cardResponseDto);
    return cardResponseDto;
  }

  @Override
  public Page<CardResponseDto> findAllByTypeCard(String payment, String type, Pageable pageable) {
    log.trace("Receiving request for getting all cards by type card");

    Page<CardResponseDto> result =
        cardService.findAllByTypeCard(payment, type, pageable).map(cardMapper::toCardResponseDto);

    log.trace("Returning page of cards: {}", result.getContent());
    return result;
  }

  /**
   * End-point to find TypeCard entity by id.
   *
   * @param id
   * @return
   */
  @Override
  public TypeCardResponseDto findTypeCardById(UUID id) {
    log.info("Find card type by id : {}", id);
    return typeCardMapper.typeCard2TypeCardResponseDto(cardService.getTypeCard(id));
  }

  /**
   * End-point to update existing TypeCard.
   *
   * @param typeCardUpdateDto
   * @return
   */
  @Override
  public TypeCardResponseDto updateTypeCard(TypeCardUpdateDto typeCardUpdateDto) {
    log.info("Receiving card type : {}", typeCardUpdateDto);
    TypeCard typeCard = typeCardMapper.typeCardUpdateDto2TypeCard(typeCardUpdateDto);

    return typeCardMapper.typeCard2TypeCardResponseDto(cardService.updateTypeCard(typeCard));
  }

  /**
   * End-point to find all Cards for the specific owner.
   *
   * @param id - owner id
   * @param pageable
   * @return
   */
  @Override
  public Page<CardResponseDto> findAllByOwner(UUID id, Pageable pageable) {
    log.trace("Receiving request for getting all cards by owner");

    Page<CardResponseDto> result =
        cardService.findByOwnerId(id, pageable).map(cardMapper::toCardResponseDto);

    log.trace("Returning page of cards: {}", result.getContent());

    return result;
  }

  /**
   * End-point to find all Cards for the specific owner except chosen card and cards who have the
   * same account
   *
   * @param ownerId - owner id
   * @param cardId
   * @param pageable
   * @return
   */
  @Override
  public Page<CardResponseDto> findAllExceptChosenByOwnerId(UUID ownerId, UUID cardId, Pageable pageable) {
    return cardService.findByOwnerIdExceptCard(ownerId, cardId, pageable)
            .map(cardMapper::toCardResponseDto);
  }

  /**
   * End-point to find all Cards for the current user.
   *
   * @param pageable
   * @return
   */
  @Override
  public Page<CardResponseDto> findAllByCurrentUser(Pageable pageable) {

    log.trace("Receiving request for getting all cards by owner");
    UUID userId = registrationClient.getUserPersonalData().getUser().getId();
    Page<CardResponseDto> result = cardService.findByOwnerId(userId, pageable).map(cardMapper::toCardResponseDto);

    log.trace("Returning page of cards: {}", result.getContent());

    return result;
  }

  /**
   * End-point to find all Cards for the current user except chosen card and cards who have the same account
   * @param cardId
   * @param pageable
   * @return
   */
  @Override
  public Page<CardResponseDto> findAllExceptChosenByCurrentUser(UUID cardId, Pageable pageable) {
    UUID userId = registrationClient.getUserPersonalData().getUser().getId();
    Page<CardResponseDto> result = cardService.findByOwnerIdExceptCard(userId, cardId, pageable)
            .map(cardMapper::toCardResponseDto);
    return result;
  }

  /**
   * End-point to get information about Card by card's number
   *
   * @param twelveNums
   * @param fourNums
   * @return
   */
  @Override
  public CardCredResponseDto findCardByCardNumber(String twelveNums, String fourNums) {
    log.info("Receiving request on getting info about Card by card's number ***{}", fourNums);
    try {
      Card card = cardService.findByNums(twelveNums, fourNums);
      CardCredResponseDto credResponseDto = cardMapper.toCardCredResponseDto(card);

      log.info("Response info with info about card with card's number ***{}", fourNums);
      return credResponseDto;
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found", e);
    }
  }
}