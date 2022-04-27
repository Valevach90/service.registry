package com.andersen.banking.service.registry.meeting_impl.service.processing.impl;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import com.andersen.banking.service.registry.meeting_impl.service.processing.UserProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProcessingServiceImpl implements UserProcessingService {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public UserDto findByIdUserDto(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsersDto() {
        return null;
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        log.info("Try to update user: {}", userDto);

        UserEntity userUpdateEntity = userMapper.toUserEntity(userDto);

        Optional<UserEntity> user = userService.findById(id);

        userUpdateEntity.setId(id);
        userUpdateEntity.setFirstName(user.get().getFirstName());

        userMapper.updateUserDetails(user, userUpdateEntity);

        userService.saveUser(user);

        log.info("Return updated user success : {}", user);
    }

}
