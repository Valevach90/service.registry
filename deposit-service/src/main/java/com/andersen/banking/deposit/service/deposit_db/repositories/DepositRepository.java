package com.andersen.banking.deposit.service.deposit_db.repositories;

import com.andersen.banking.deposit.service.deposit_db.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
