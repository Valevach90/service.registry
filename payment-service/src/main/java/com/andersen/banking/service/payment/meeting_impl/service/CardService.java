package com.andersen.banking.service.payment.meeting_impl.service;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

  Card findById(Long id);

  /**
   * Find all users.
   *
   * @param pageable page object
   * @return page of users
   */
  Page<Card> findAll(Pageable pageable);

  /**
   * Update user.
   *
   * @param user user entity to update
   */
  void update(Card user);

  /**
   * Delete user by Id.
   *
   * @param id Id of user
   */
  void deleteById(Long id);

  /**
   * Create new user.
   *
   * @param user user to create
   * @return passport
   */
  Card create(Card user);
}
