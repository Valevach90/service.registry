package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    boolean existsByTransferIdAndStatus(UUID transferId, int status);
}
