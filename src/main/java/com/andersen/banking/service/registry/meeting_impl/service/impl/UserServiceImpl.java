package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        log.debug("Find user by id: {}", id);

        Optional<User> result = userRepository.findById(id);

        log.debug("Return address: {}", result);
        return result;

    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        log.info("Find all users for pageable: {}", pageable);

        Page<User> allUsers = userRepository.findAll(pageable);

        log.info("Found {} users", allUsers.getContent().size());
        return allUsers;
    }

    @Override
    @Transactional
    public void update(User updatedUser) {
        log.debug("Trying to update user: {}", updatedUser);

        userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new NotFoundException(User.class, updatedUser.getId()));

        userRepository.save(updatedUser);

        log.debug("Return updated User: {}", updatedUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleting user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        userRepository.deleteById(id);

        log.info("deleted user: {} with id: {}", user, id);
    }

    @Override
    @Transactional
    public User create(User user) {
        log.info("creating user: {}", user);

        user.setId(null);
        User savedUser = userRepository.save(user);

        log.info("created user: {}", savedUser);
        return savedUser;
    }
}
