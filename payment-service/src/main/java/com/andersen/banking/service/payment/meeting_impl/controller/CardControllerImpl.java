package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.CardController;
import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.mapping.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.mapping.CardRegistrationDtoMapper;
import com.andersen.banking.service.payment.meeting_impl.service.impl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for CardController.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerImpl implements CardController {

  private final CardServiceImpl cardService;
  private final CardMapper cardMapper;
  private final CardRegistrationDtoMapper cardRegistrationDtoMapper;

  /**
   * Controller to find Card entity by ud.
   *
   * @param id
   * @return
   */
  @Override
  public CardDto findById(@PathVariable Long id) {
    log.trace("Receiving card id: {}", id);

    CardDto cardDto = cardMapper.toCardDto(cardService.findById(id));

    log.trace("Returning card with id: {}", id);
    return cardDto;
  }

  /**
   * Controller to find all Card entities.
   *
   * @param pageable
   * @return
   */
  @Override
  public Page<CardDto> findAll(Pageable pageable) {
    log.trace("Receiving request for all cards");

    Page<CardDto> result = cardService.findAll(pageable)
        .map(cardMapper::toCardDto);

    log.trace("Returning list of cards: {}", result.getContent());
    return result;
  }

  /**
   * Controller to update existing Card.
   *
   * @param cardDto
   * @return
   */
  @Override
  public CardDto updateCard(CardDto cardDto) {
    log.trace("Receiving card: {}", cardDto);

    Card cardToUpdate = cardMapper.toCard(cardDto);
    CardDto updatedCardDto = cardMapper.toCardDto(cardService.update(cardToUpdate));

    log.trace("Returning updated card: {}", updatedCardDto);
    return updatedCardDto;
  }

  /**
   * Controller to delete Card.
   *
   * @param id
   * @return
   */
  @Override
  public CardDto deleteById(Long id) {
    log.trace("Receiving card id {}", id);

    Card deletedCard = cardService.deleteById(id);

    log.trace("Returning deleted card with id: {}", id);
    return cardMapper.toCardDto(deletedCard);
  }

  /**
   * Controller to register new Card.
   *
   * @param cardDto
   * @return
   */
  @Override
  public CardDto create(CardRegistrationDto cardDto) {
    log.trace("Receiving request for creating card card: {}", cardDto);

    Card cardToCreate = cardRegistrationDtoMapper.toCard(cardDto);

    CardDto savedCard = cardMapper.toCardDto(cardService.create(cardToCreate));

    log.trace("Returning created card: {}", savedCard);
    return savedCard;
  }
}
