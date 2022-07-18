package com.andersen.banking.deposit.service.deposit_db.repositories;

import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositProductRepository extends JpaRepository<DepositProduct, Long> {
}
