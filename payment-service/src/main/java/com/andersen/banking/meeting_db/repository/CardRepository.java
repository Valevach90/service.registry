package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.Card;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** JpaRepository, which works with Card entity. */
public interface CardRepository extends JpaRepository<Card, UUID> {

    Optional<Card> findByFirstTwelveNumbersAndLastFourNumbers(String f, String s);

    Page<Card> getCardByAccountId(UUID id, Pageable pageable);

    List<Card> findAllByAccount_Id(UUID id);

    @Query(
            value =
                    "SELECT c FROM Card c "
                            + "JOIN c.cardProduct.typeCard tc "
                            + "WHERE (:payment IS NULL OR tc.paymentSystem = :payment) "
                            + "   AND (:type IS NULL OR tc.typeName = :type)")
    Page<Card> findCardByPaymentSystemAndType(
            @Param("payment") String payment, @Param("type") String type, Pageable pageable);

    Page<Card> findCardByAccount_OwnerId(UUID ownerId, Pageable pageable);

    Page<Card> findByAccount_OwnerIdAndAccount_IdNot(UUID ownerId, UUID id, Pageable pageable);

    boolean existsByFirstTwelveNumbersAndLastFourNumbers(String firstTwelve, String lastFour);

    @Query(value = "SELECT * FROM card "
            + "WHERE is_active = true AND expire_date < CURRENT_DATE "
            + "for update skip locked "
            + "LIMIT 10", nativeQuery = true)
    List<Card> findCardsToDeactivate();
}
