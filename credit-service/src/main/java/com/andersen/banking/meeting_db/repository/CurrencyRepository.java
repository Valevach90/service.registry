package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Currency;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

}
