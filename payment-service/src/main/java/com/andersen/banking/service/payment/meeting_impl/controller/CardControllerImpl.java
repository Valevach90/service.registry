package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.controller.CardController;
import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.mapping.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.service.impl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerImpl implements CardController {

  private final CardServiceImpl cardService;
  private final CardMapper cardMapper;

  @Override
  public CardDto findById(@PathVariable Long id) {
    log.trace("Find card by Id: {}", id);

    CardDto result = cardMapper.toCardDto(cardService.findById(id));

    log.trace("Return userDto: {}", result);
    return result;
  }

  @Override
  public Page<CardDto> findAll(Pageable pageable) {
    log.trace("Find all cards");

    Page<CardDto> result = cardService.findAll(pageable)
        .map(cardMapper::toCardDto);

    log.trace("Return list of cardDto: {}", result.getContent());
    return result;
  }

  @Override
  public void updateCard(CardDto cardDto) {
    log.trace("Try to update card: {}", cardDto);

    Card updatedCard = cardMapper.toCard(cardDto);
    cardService.update(updatedCard);

    log.trace("Updated card successfully");
  }

  @Override
  public void deleteById(Long id) {
    log.trace("Try to delete card with id: {}", id);

    cardService.deleteById(id);

    log.trace("Deleted card with id: {}", id);
  }

  @Override
  public CardDto create(CardDto cardDto) {
    log.debug("Trying to create card: {}", cardDto);

    Card card = cardMapper.toCard(cardDto);

    Card savedCard = cardService.create(card);

    CardDto savedCardDto = cardMapper.toCardDto(savedCard);

    log.debug("Created card: {}", savedCardDto);
    return savedCardDto;
  }
}
