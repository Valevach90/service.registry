package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_db.entities.TransferKafkaMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferKafkaMessageRepository extends JpaRepository<TransferKafkaMessage, Long> {
}
