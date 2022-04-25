package com.andersen.banking.service.registry.service;

import com.andersen.banking.service.registry.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * User service for searching and working with the entity User
 *
 */

@Service
public interface UserService {


    /**
     * Find all user
     *
     * @param pageable
     * @return pages
     */
    Page<UserEntity> findAll(Pageable pageable);

    /**
     * Save user
     *
     * @param user
     * @return user
     */
    Optional<UserEntity> save(UserEntity user);

    /**
     * Find user by id
     *
     * @param id
     * @return user
     */
     Optional<UserEntity> findById(Long id);

    /**
     * Update user by id
     *
     * @param id
     * @param user
     * @return user
     */
    Optional<UserEntity> update(Long id, UserEntity user);

    /**
     * Delete user by id
     *
     * @param id
     * @return boolean
     */
    void delete(Long id);
}
