package com.andersen.banking.service.payment.meeting_db.repository;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JpaRepository, which works with Card entity.
 */

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByFirstTwelveNumbersAndLastFourNumbers(String f, String s);

    Page<Card> getCardByAccountId(Long id, Pageable pageable);

    @Query(value = "SELECT c FROM Card c " +
            "JOIN c.typeCard tc " +
            "WHERE (:payment IS NULL OR tc.paymentSystem = :payment) " +
            "   AND (:type IS NULL OR tc.typeName = :type)")
    Page<Card> nat(
            @Param("payment") String payment,
            @Param("type") String type,
            Pageable pageable
    );
}
