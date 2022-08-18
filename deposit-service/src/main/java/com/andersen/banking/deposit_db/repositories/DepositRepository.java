package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Page<Deposit> findDepositByUserId(Long userId, Pageable pageable);
}
