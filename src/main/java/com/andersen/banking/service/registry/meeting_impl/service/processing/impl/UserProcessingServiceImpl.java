package com.andersen.banking.service.registry.meeting_impl.service.processing.impl;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.processing.UserProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProcessingServiceImpl implements UserProcessingService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto findByIdUserDto(Long userId) {
        log.debug("Find user dto by id: {}", userId);

        User user = userRepository.getById(userId);
        UserDto userDto = userMapper.toUserDto(user);

        log.debug("Return user dto success: {}", userDto);
        return userDto;
    }

    @Override
    public Page<UserDto> findAllUsersDto(Pageable pageable) {
        log.debug("Find user list dto by id: {}");

        Page<User> users = userRepository.findAll(pageable);
        Page<UserDto> usersDto = userMapper.toListDtoUsers(users);

        log.debug("Return user dto success: {}", usersDto);
        return usersDto;
    }
}
