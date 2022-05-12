package com.andersen.banking.service.registry.meeting_impl.service.local.impl;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.SaveExistedException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Page<User> findAll(Pageable pageable) {
        log.debug("Find all users");

        Page<User> users = userRepository.findAll(pageable);

        log.debug("Return counts all users with pageable success: {}", users.getContent().size());
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        log.debug("Find user by id: {}", id);

        Optional<User> user = userRepository.findById(id);

        log.debug("Return user find by id success: {}", user);
        return user;
    }

    @Override
    public User saveUser(User user) {
        log.debug("Saving user in database: {}", user);
        findById(user.getId()).ifPresent(usr -> {
            throw new SaveExistedException(User.class, user.getId());
        });
        User savedUser = userRepository.save(user);

        log.debug("Return saved user success: {}", savedUser);
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        log.debug("Update user: {}", user);

        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(User.class, user.getId()));

        userRepository.save(user);
        log.debug("Update user success: {}", user);

        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        log.debug("Delete user by id: {}", id);

        findById(id).ifPresent(user -> userRepository.deleteById(user.getId()));

        log.debug("Delete user by id success: {}", id);
        return true;
    }
}
