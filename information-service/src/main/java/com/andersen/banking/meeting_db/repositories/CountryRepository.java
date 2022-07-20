package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> getCountriesByDeletedIsFalse();
}
