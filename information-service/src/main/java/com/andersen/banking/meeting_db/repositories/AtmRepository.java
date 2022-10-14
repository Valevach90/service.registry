package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Atm;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<Atm, UUID> {

}
