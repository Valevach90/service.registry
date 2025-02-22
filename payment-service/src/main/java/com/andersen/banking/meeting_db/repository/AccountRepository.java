package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.Account;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

public interface AccountRepository extends JpaRepository<Account, UUID>,
        RevisionRepository<Account, UUID, Integer> {

    Page<Account> findAccountByOwnerId(UUID id, Pageable pageable);

    @Query(value = "SELECT * FROM account "
            + "WHERE is_active = true AND close_date < CURRENT_DATE "
            + "for update skip locked "
            + "LIMIT 10", nativeQuery = true)
    List<Account> findAccountsToDeactivate();
}
