package com.andersen.banking.service.payment.meeting_impl.service;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface presents the basic contract for service, which works with Card entity.
 */
public interface CardService {

  /**
   * This method returns Card entity with the given id.
   *
   * @param id
   * @return
   */
  Card findById(Long id);

  /**
   * This method returns page of Card entities.
   *
   * @param pageable
   * @return
   */
  Page<Card> findAll(Pageable pageable);

  /**
   * This method updates the given card and returns updated version.
   *
   * @param card
   * @return
   */
  Card update(Card card);

  /**
   * This method deletes the Card with the given id and the returns deleted entity.
   *
   * @param id
   * @return
   */
  Card deleteById(Long id);

  /**
   * This method registers new Card.
   *
   * @param card
   * @return
   */
  Card create(Card card);
}
