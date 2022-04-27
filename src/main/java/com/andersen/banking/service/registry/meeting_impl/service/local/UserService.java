package com.andersen.banking.service.registry.meeting_impl.service.local;

import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
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
    List<UserEntity> findAll();

    /**
     * Find by id user and return user from database
     *
     * @param id
     * @return UserEntity
     */
    Optional<UserEntity> findById(Long id);

    /**
     * Save user in database and return user
     *
     * @param user
     * @return UserEntity
     */
    Optional<UserEntity> saveUser(Optional<UserEntity> user);

    /**
     * Update user from database by id
     *
     * @param id
     * @param user
     * @return UserEntity
     */
    Optional<UserEntity> updateUser(Long id, UserEntity user);


    /**
     * Delete user from database by id
     *
     * @param id
     * @return boolean
     */
    boolean deleteUser(Long id);

}
