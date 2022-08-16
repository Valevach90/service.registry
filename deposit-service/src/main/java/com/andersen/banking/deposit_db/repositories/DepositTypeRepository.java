package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.DepositType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositTypeRepository extends JpaRepository<DepositType, Long> {
}
