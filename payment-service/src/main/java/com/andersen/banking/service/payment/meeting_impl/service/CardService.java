package com.andersen.banking.service.payment.meeting_impl.service;

import com.andersen.banking.service.payment.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * This method returns page of cards by account_id.
     *
     * @param id
     * @return
     */
    Page<Card> findByAccountId(Long id, Pageable pageable);

    /**
     * This method returns page of card by payment or type or all together. If type and payment system is null
     * return all cards
     *
     * @param payment
     * @param type
     * @param pageable
     * @return
     */
    Page<Card> findAllByTypeCard(String payment, String type, Pageable pageable);

    /**
     * This method returns TypeCardResponseDto entity with the given id.
     *
     * @param id
     * @return
             */
    TypeCardResponseDto getTypeCard(Long id);

    /**
     * This method updates the given card type and returns updated version.
     *
     * @param typeCardUpdateDto
     * @return
     */
    TypeCardResponseDto updateTypeCard(TypeCardUpdateDto typeCardUpdateDto);
}
