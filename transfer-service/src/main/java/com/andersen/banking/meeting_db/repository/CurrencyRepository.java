package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
    Currency findByName(String name);
}
