package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    Optional<Deposit> findByDepositNumber(String depositNumber);
}
