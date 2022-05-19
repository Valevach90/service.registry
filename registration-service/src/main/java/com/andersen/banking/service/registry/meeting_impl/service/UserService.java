package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service for working with address.
 */
public interface UserService {
    /**
     * Find user by Id.
     *
     * @param id Id of user
     * @return User
     */
    Optional<User> findById(Long id);

    /**
     * Find all users.
     *
     * @param pageable page object
     * @return page of users
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Update user.
     *
     * @param user user entity to update
     */
    void update(User user);

    /**
     * Delete user by Id.
     *
     * @param id Id of user
     */
    void deleteById(Long id);

    /**
     * Create new user.
     *
     * @param user user to create
     * @return passport
     */
    User create(User user);
}
