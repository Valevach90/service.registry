package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferStatusRepository extends JpaRepository<TransferStatus, UUID> {

}
