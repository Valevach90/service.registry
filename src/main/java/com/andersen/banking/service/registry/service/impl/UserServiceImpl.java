package com.andersen.banking.service.registry.service.impl;

import com.andersen.banking.service.registry.domain.UserEntity;
import com.andersen.banking.service.registry.repository.UserRepository;
import com.andersen.banking.service.registry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User service implementation
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users;
    }

    @Override
    public Optional<UserEntity> save(UserEntity user) {
        UserEntity savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> update(Long id, UserEntity newUser) {
        UserEntity updatedUser = userRepository.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPatronymic(newUser.getPatronymic());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            return userRepository.save(user);
        }).orElseThrow(()->{
            return new RuntimeException("The problem to update user entity in service lay with id: " + id);
        });
        return Optional.ofNullable(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
