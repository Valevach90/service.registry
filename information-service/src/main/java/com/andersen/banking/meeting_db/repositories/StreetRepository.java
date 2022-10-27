package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Street;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {

    Optional<Street> findByName(String name);

    List<Street> getTimeTableByCity_IdAndDeletedIsFalse(Long cityId);

    List<Street> findAllByCity_IdAndNameIn(Long cityId, List<String> names);
}
