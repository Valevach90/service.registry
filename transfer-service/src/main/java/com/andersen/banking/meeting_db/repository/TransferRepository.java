package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Transfer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findTransfersByUserId(Long userId);
}
