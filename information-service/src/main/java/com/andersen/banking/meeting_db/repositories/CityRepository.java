package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> getCitiesByCountry_IdAndDeletedIsFalse(Long countryId);
}
