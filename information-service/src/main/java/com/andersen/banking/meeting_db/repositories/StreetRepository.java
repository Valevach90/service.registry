package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {

    List<Street> getTimeTableByCity_IdAndDeletedIsFalse(Long cityId);
}
