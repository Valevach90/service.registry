package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Card;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** This interface presents the basic contract for service, which works with Card entity. */
public interface CardService {

    /**
     * This method returns Card entity with the given id.
     *
     * @param id
     * @return
     */
    Card findById(UUID id);

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
     * This method deactivates the Card with the given id and the returns deleted entity.
     *
     * @param id
     * @return
     */
    Card deactivateById(UUID id);

    /**
     * This method deactivates some amount of cards that were expired. Returns true if deactivated some cards.
     *
     * @param
     * @return
     */
    boolean deactivateSomeExpiredCards();

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
    Page<Card> findByAccountId(UUID id, Pageable pageable);

    /**
     * This method returns page of card by payment or type or all together. If type and payment
     * system is null return all cards
     *
     * @param payment
     * @param type
     * @param pageable
     * @return
     */
    Page<Card> findAllByTypeCard(String payment, String type, Pageable pageable);

    /**
     * This method returns page of cards by owner_id.
     *
     * @param id
     * @return
     */
    Page<Card> findByOwnerId(UUID id, Pageable pageable);

    /**
     * This method returns page of cards by owner_id except card in use.
     *
     * @param id
     * @return
     */
    Page<Card> findByOwnerIdExceptCard(UUID id, UUID cardId, Pageable pageable);

    /**
     * This method return Card by not hashed card's numbers
     *
     * @param twelveNums
     * @param fourNums
     * @return
     */
    Card findByNotHashedNums(String twelveNums, String fourNums);

    /**
     * This method return Card by hashed card's numbers
     *
     * @param twelveNotHashedNums
     * @param fourNums
     * @return
     */
    Card findByHashedNums(String twelveNotHashedNums, String fourNums)
            throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
}
