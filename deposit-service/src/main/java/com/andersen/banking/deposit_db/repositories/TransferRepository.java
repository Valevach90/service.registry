package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
