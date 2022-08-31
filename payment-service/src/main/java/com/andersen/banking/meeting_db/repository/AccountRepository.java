package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAccountByOwnerId(Long id, Pageable pageable);

}
