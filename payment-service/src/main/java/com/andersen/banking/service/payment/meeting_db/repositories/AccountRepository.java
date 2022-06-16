package com.andersen.banking.service.payment.meeting_db.repositories;

import com.andersen.banking.service.payment.meeting_db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
