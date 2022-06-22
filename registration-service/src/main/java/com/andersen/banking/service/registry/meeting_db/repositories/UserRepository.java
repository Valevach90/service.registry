package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
