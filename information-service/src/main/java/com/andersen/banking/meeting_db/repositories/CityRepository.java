package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.City;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    List<City> getCitiesByCountry_IdAndDeletedIsFalse(Long countryId, Pageable pageable);

    @Query(value = "select * from city where id in (select city_id from bank_branch where deleted=false) and deleted = false and country_id=:countryId", nativeQuery = true)
    List<City> getOnlyCitiesWithBankBranches(@Param("countryId") Long countryId);
}
