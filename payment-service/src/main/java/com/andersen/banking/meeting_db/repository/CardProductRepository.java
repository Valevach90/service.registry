package com.andersen.banking.meeting_db.repository;


import com.andersen.banking.meeting_db.entities.CardProduct;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardProductRepository extends JpaRepository<CardProduct, UUID> {
}
