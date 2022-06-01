package com.andersen.banking.service.payment.meeting_db.repository;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
