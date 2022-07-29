package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
