package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {

    List<BankBranch> getBankBranchByCity_IdAndDeletedIsFalse(Long cityId);
}
