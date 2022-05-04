package com.andersen.banking.service.registry.meeting_impl.service.local;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with user.
 *
 * @author Aleksei Sidorin
 * @version 1.0
 */

@Service
public interface UserService {


    /**
     * Find all user
     *
     * @return List<UserEntity>
     */
    List<User> findAll();

    /**
     * Find by id user and return user from database
     *
     * @param id
     * @return UserEntity
     */
    Optional<User> findById(Long id);

    /**
     * Save user in database and return user
     *
     * @param user
     * @return UserEntity
     */
    Optional<User> saveUser(User user);

    /**
     * Update user from database by id
     *
     * @param id
     * @param user
     * @return UserEntity
     */
    Optional<User> updateUser(Long id, User user);


    /**
     * Delete user from database by id
     *
     * @param id
     * @return boolean
     */
    boolean deleteUser(Long id);

}
