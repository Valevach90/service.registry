package com.andersen.banking.service.registry.meeting_impl.service.processing.impl;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.processing.UserProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserProcessingServiceImplTest {

    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "firstName";
    @MockBean
    private UserMapper userMapper;

    private final UserProcessingService processingService;
    @MockBean
    private final UserRepository userRepository;


    @Autowired
    UserProcessingServiceImplTest(UserMapper userMapper, UserProcessingService processingService, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.processingService = processingService;
        this.userRepository = userRepository;
    }


    @Test
    void findByIdUserDto() {
        User user = new User(1L, "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");
        UserDto userDtoActual = new UserDto( "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        when(userRepository.getById(1L)).thenReturn(user);
        when(userMapper.toUserDto(user)).thenReturn(userDtoActual);
        UserDto userDtoExpected = processingService.findByIdUserDto(1L);

        assertEquals(userDtoExpected, userDtoActual);
    }

    @Test
    void findAllUsersDto() {
        List<User> users = List.of( new User(1L, "Jack", "Nicholson", "Strong", "jack@mail.com", "555-55-55"),
                new User(2L, "Mickael", "Jackson", "Dancer", "mickael@mail.com", "888-88-88"),
                new User(3L, "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77"));

        List<UserDto> usersDto = List.of( new UserDto( "Jack", "Nicholson", "Strong", "jack@mail.com", "555-55-55"),
                new UserDto( "Mickael", "Jackson", "Dancer", "mickael@mail.com", "888-88-88"),
                new UserDto("Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77"));

        Pageable pageableUser = createPageable();
        Page<User> pageUsers = new PageImpl<>(users, pageableUser, SIZE_PAGE);

        Pageable pageableUserDto = createPageable();
        Page<UserDto> pageUsersDto = new PageImpl<>(usersDto, pageableUserDto, SIZE_PAGE);

        when(userRepository.findAll(pageableUser)).thenReturn(pageUsers);
        when(userMapper.toListDtoUsers(pageUsers)).thenReturn(pageUsersDto);

        Page<UserDto> userDtoExpected = processingService.findAllUsersDto(pageableUser);

        assertEquals(userDtoExpected, pageUsersDto);
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }
}