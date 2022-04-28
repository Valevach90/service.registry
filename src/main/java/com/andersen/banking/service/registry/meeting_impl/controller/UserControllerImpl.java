package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public List<UserEntity> findAll() {
        log.trace("Find all users");

        List<UserEntity> users = userService.findAll();

        log.trace("Return list of users success: {}", users);
        return users;
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        log.trace("Find user by Id: {}", id);

        Optional<UserEntity> user = userService.findById(id);

        log.trace("Return user success: {}", user);

        return user;
    }

    @Override
    public Optional<UserEntity> saveUser(UserEntity newUser) {
        log.trace("Saving new user in database: {}", newUser);

        userService.findById(newUser.getId()).ifPresent(user -> {
            throw new RuntimeException("The user with id: " + newUser.getId() + " is already saved before");
        });

        Optional<UserEntity> savedUser = Optional.ofNullable(userService.saveUser(newUser).orElseThrow(() -> {
            throw new RuntimeException("The user with id " + newUser.getId() + " is successful saved");
        }));

        log.trace("Save new user success in database: {}", newUser);
        return savedUser;
    }

    @Override
    public void updateUser(Long id, UserEntity user) {
        log.trace("Try to update user: {}", user);

        userService.updateUser(id, user);

        log.trace("Updated user success");

    }

    @Override
    public void deleteUserById(Long id) {
        log.trace("Delete user with id: {}", id);

        Optional.ofNullable(userService.findById(id).orElseThrow(() -> {
            throw new NotFoundException(NotFoundException.BY_ID);
        }));

        userService.deleteUser(id);
        log.trace("Delete success user with id: {}", id);
    }
}
