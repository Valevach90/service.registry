package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.City;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    List<City> getCitiesByCountry_IdAndDeletedIsFalse(Long countryId, Pageable pageable);

    Optional<City> findByName(String name);

    @Query(value = "SELECT * FROM city "
            + "WHERE id IN "
            + "(SELECT city_id FROM bank_branch WHERE deleted=false) "
            + "AND deleted = false AND country_id=:countryId", nativeQuery = true)
    List<City> getOnlyCitiesWithBankBranches(@Param("countryId") Long countryId);

    @Query(value = "SELECT * FROM city "
            + "WHERE country_id IN :ids "
            + "AND city_name IN :names", nativeQuery = true)
    List<City> findAllCitiesByNameAndIdCountry(@Param("names") List<String> names,
            @Param("ids") List<Long> ids);

    boolean existsByName(String name);

    City getCityByName(String name);
}
