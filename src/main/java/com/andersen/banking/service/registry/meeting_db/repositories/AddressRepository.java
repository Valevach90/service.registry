package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    /**
     * Find address with user id.
     *
     * @param userId address for filtering results
     * @return address with filtration by user id
     */
    Optional<AddressEntity> findAddressByUserId(Long userId);
}
