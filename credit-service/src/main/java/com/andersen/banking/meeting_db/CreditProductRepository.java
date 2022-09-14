package com.andersen.banking.meeting_db;

import com.andersen.banking.meeting_api.CreditProductDTO;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditProductRepository extends JpaRepository<CreditProduct, UUID> {
}
