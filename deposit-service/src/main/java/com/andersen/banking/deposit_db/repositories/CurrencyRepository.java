package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.Currency;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
}
