package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Find address with user id.
     *
     * @param userId address for filtering results
     * @return address with filtration by user id
     */
    Optional<Address> findAddressByUserId(Long userId);
}
