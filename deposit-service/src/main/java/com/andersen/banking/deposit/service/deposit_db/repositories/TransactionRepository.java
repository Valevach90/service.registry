package com.andersen.banking.deposit.service.deposit_db.repositories;

import com.andersen.banking.deposit.service.deposit_db.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
