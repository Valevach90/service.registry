package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Currency;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    Optional<Currency> findByName(String name);
}
