package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Deposit;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    Page<Deposit> findDepositByUserId(UUID userId, Pageable pageable);

    Optional<Deposit> findByDepositNumber(String depositNumber);

    @Query("SELECT count(*) FROM Deposit")
    Integer getNumberOfDeposits();
}
