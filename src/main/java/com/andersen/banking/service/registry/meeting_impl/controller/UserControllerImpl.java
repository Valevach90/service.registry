package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import com.andersen.banking.service.registry.meeting_impl.service.processing.UserProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserProcessingService processingService;


    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.trace("Find all users");

        Page<User> users = userService.findAll(pageable);
        List<User> listUsers = users.getContent();

        Page<UserDto> usersDto = new PageImpl<>(userMapper.toListDtoUsers(listUsers));

        log.trace("Return list of users success: {}", users.getContent());
        return usersDto;
    }

    @Override
    public UserDto findUserById(Long id) {
        log.trace("Find user by Id: {}", id);

        Optional<User> user = userService.findById(id);

        UserDto userDto = userMapper.toUserDto(user
                .orElseThrow(() -> new NotFoundException(User.class, id)));

        log.trace("Return user success: {}", user);

        return userDto;
    }

    @Override
    public void saveUser(UserDto newUserDto) {
        log.trace("Saving new user in database: {}", newUserDto);

        User savedUser = userMapper.toUser(newUserDto);
        userService.saveUser(savedUser);

        log.trace("Save new user success in database: {}", newUserDto);
    }

    @Override
    public void updateUser(UserDto userDto) {
        log.trace("Try to update user: {}", userDto);

        User user = userMapper.toUser(userDto);
        userService.updateUser(user);

        log.trace("Updated user success");
    }

    @Override
    public void deleteUserById(Long id) {
        log.trace("Delete user with id: {}", id);

        userService.deleteUser(id);

        log.trace("Delete success user with id: {}", id);
    }

    @Override
    public Page<UserDto> findAllDto(Pageable pageable) {
        log.info("Find users list by id dto: {}");

        Page<UserDto> usersListDto = processingService.findAllUsersDto(pageable);

        log.info("Return userDto: {}", usersListDto.getContent());
        return usersListDto;
    }

    @Override
    public UserDto findUserByIdUserDto(Long id) {
        log.info("Find user by id dto: {}", id);

        UserDto userDto = processingService.findByIdUserDto(id);

        log.info("Return userDto: {}", userDto);
        return userDto;
    }


}
