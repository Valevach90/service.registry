package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.TransferLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferLogRepository extends JpaRepository<TransferLog, UUID> {

    boolean existsById(UUID id);
}
