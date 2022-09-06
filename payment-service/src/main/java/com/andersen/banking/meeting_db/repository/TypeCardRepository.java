package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TypeCardRepository extends JpaRepository<TypeCard, UUID> {

  Optional<TypeCard> findByPaymentSystemAndTypeName(String ps, String tn);
}
