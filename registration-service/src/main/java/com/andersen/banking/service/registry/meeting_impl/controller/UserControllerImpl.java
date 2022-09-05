package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.trace("Find all users");

        Page<UserDto> result = userService.findAll(pageable)
                .map(userMapper::toUserDto);

        log.trace("Return list of userDto: {}", result.getContent());
        return result;
    }

    @Override
    public UserDto findById(UUID id) {
        log.trace("Find user by Id: {}", id);

        UserDto result = userService.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        log.trace("Return userDto: {}", result);

        return result;
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Try to create user: {}", userDto);

        User user = userMapper.toUser(userDto);

        User savedUser = userService.create(user);

        UserDto savedUserDto = userMapper.toUserDto(savedUser);

        log.debug("created user: {}", savedUserDto);
        return savedUserDto;
    }

    @Override
    public void updateUser(UserDto userDto) {
        log.trace("Try to update user: {}", userDto);

        User addressUpdated = userMapper.toUser(userDto);
        userService.update(addressUpdated);

        log.trace("Updated user success");
    }

    @Override
    public void deleteById(UUID id) {
        log.trace("Try to delete user with id: {}", id);

        userService.deleteById(id);

        log.trace("Deleted user with id: {}", id);
    }
}
