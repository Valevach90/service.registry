package com.andersen.banking.service.registry.meeting_impl.service;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    User findById(UUID id);

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
     * Update users email.
     *
     * @param user with new email to update
     */
    void updateEmail(User user);

    /**
     * Delete user by Id.
     *
     * @param id Id of user
     */
    void deleteById(UUID id);

    /**
     * Create new user.
     *
     * @param user user to create
     * @return passport
     */
    User create(User user);

    /**
     * Find user with helping email
     *
     * @param email
     * @return
     */
    User findByEmail(String email);
}
