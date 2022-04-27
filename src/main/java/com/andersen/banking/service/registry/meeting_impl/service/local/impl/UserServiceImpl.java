package com.andersen.banking.service.registry.meeting_impl.service.local.impl;

import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserEntity> findAll() {
        log.debug("Find all users");

        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();

        log.debug("Return counts all users with pageable success: {}", users.size());
        return users;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        log.debug("Find user by id: {}", id);

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.BY_ID));
        log.debug("Return user find by id success: {}", user);
        return Optional.of(user);
    }

    @Override
    public Optional<UserEntity> saveUser(Optional<UserEntity> user) {
        log.debug("Saving user in database: {}", user);

        UserEntity savedUser = userRepository.save(user);

        log.debug("Return saved user success: {}", savedUser);
        return Optional.of(savedUser);
    }

    @Override
    public Optional<UserEntity> updateUser(Long id, UserEntity newUser) {
        log.debug("Update user by id: {}", id);

        userRepository.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPatronymic(newUser.getPatronymic());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            return userRepository.save(user);
        }).orElseThrow(() -> {
            return new RuntimeException("Problem with update user by id " + id);
        });
        log.debug("Update user by id success: {}", newUser);
        return Optional.of(newUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        log.debug("Delete user by id: {}", id);

        if (userRepository.findById(id) != null) {
            userRepository.deleteById(id);
            return true;

        } else {
            throw new NotFoundException(NotFoundException.BY_ID);
        }
    }
}
