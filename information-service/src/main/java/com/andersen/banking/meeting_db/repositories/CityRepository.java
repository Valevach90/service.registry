package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> getCitiesByCountry_IdAndDeletedIsFalse(Long countryId, Pageable pageable);

    @Query(value = "select * from city where id in (select city_id from bank_branch where deleted=false) and deleted=false;", nativeQuery = true)
    List<City> getOnlyCitiesWithBankBranches(Long countryId);
}
