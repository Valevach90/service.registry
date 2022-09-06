package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.Address;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Find address with user id.
     *
     * @param userId address for filtering results
     * @return address with filtration by user id
     */
    Optional<Address> findAddressByUserId(UUID userId);
}
