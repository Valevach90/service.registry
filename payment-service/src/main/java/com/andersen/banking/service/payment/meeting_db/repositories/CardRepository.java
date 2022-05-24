package com.andersen.banking.service.payment.meeting_db.repositories;

import com.andersen.banking.service.payment.meeting_db.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
