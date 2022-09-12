package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.DepositType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositTypeRepository extends JpaRepository<DepositType, Long> {

    Optional<DepositType> findByName(String name);
}
