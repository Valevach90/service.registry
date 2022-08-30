package com.andersen.banking.service.payment.meeting_db.repository;

import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeCardRepository extends JpaRepository<TypeCard, Long> {

    Optional<TypeCard> findByPaymentSystemAndTypeName(String ps, String tn);

}
