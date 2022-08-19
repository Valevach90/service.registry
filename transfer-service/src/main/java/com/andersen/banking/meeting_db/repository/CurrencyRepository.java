package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByName(String name);
}
