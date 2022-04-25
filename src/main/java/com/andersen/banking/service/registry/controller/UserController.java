package com.andersen.banking.service.registry.controller;


import com.andersen.banking.service.registry.domain.UserEntity;
import com.andersen.banking.service.registry.service.UserService;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Find all users with page
     *
     * @param pageable
     * @return pages of users
     */
    @GetMapping("/users")
    public Page<UserEntity> getUsers(Pageable pageable){
        return userService.findAll(pageable);
    }

    /**
     * Create new user in database
     *
     * @param newUser
     * @return savedUser
     */
    @PostMapping("/users")
    public UserEntity newUser(@RequestBody UserEntity newUser) {
        userService.findById(newUser.getId()).ifPresent(user -> {
            throw new RuntimeException("The user with id " + newUser.getId() + " already saved in database");
        });
        UserEntity savedUser = userService.save(newUser).orElseThrow(() -> new RuntimeException("The user with id " + newUser.getId() + " was not save in the database"));
        return savedUser;
    }


    /**
     * Get user by id from database
     *
     * @param id
     * @return user
     */
    @GetMapping("/users/{id}")
    public Optional<UserEntity> getUserById(Long id){
        Optional<UserEntity> user = Optional.ofNullable(userService.findById(id).orElseThrow(() -> new RuntimeException("The user with id " + id + " was not found in database")));
        return user;
    }


    /**
     * Update user from database on the particular fields
     *
     * @param id
     * @param changes
     * @return user
     */
    @PatchMapping("/users")
    public Optional<UserEntity> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> changes){
        UserEntity user = userService.findById(id).orElseThrow(() -> new RuntimeException("The user with id: " + id + " was not found in the database"));
        PropertyAccessor propertyAccess = PropertyAccessorFactory.forDirectFieldAccess(user);
        propertyAccess.setPropertyValues(changes);
        return userService.update(id, user);
    }

    /**
     * Delete user from database
     *
     * @param id
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.findById(id).orElseThrow(() -> new RuntimeException("The discount with id " + id + " was ot found in database"));
        userService.delete(id);
    }
}
