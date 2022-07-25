package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
