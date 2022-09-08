package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.Deposit;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    Page<Deposit> findDepositByUserId(UUID userId, Pageable pageable);

    Optional<Deposit> findByDepositNumber(String depositNumber);
}
