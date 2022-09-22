package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.CreditProduct;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditProductRepository extends JpaRepository<CreditProduct, UUID> {}
