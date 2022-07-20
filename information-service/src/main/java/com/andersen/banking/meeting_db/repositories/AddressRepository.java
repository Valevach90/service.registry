package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> getAddressByStreet_IdAndDeletedIsFalse(Long streetId);
}
