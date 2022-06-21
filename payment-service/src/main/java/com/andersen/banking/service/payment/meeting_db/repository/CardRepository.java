package com.andersen.banking.service.payment.meeting_db.repository;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JpaRepository, which works with Card entity.
 */

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByFirstTwelveNumbersAndLastFourNumbers(String f, String s);

    Page<Card> getCardByAccountId(Long id, Pageable pageable);


}
