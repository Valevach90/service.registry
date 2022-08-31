package com.andersen.banking.service.registry.meeting_impl.controller;

import com.andersen.banking.service.registry.meeting_api.controller.UserController;
import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.mapping.UserMapper;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest(classes = UserControllerImpl.class)
class UserControllerImplTest {
    private static final Long ID = 23L;
    private static final UUID UUID_ID = UUID.fromString("0d4ff469-465e-412b-9737-34d08d227464");
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "id";

    private UserDto userDto;
    private Optional<User> user;

    @Autowired
    UserController userController;
    @MockBean
    UserService userService;
    @MockBean
    UserMapper userMapper;

    @BeforeEach
    void initData() {
        user = Optional.of(new User());
        userDto = new UserDto();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(userService.findById(UUID_ID))
                .thenReturn(user);
        Mockito
                .when(userMapper.toUserDto(user.get()))
                .thenReturn(userDto);

        var result = userController.findById(UUID_ID);

        assertEquals(userDto, result);
    }

    @Test
    void whenFindAll_andOk() {
        List<User> passports = generateUser();
        Pageable pageable = createPageable();
        Page<User> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(userService.findAll(pageable))
                .thenReturn(page);
        Mockito
                .when(userMapper.toUserDto(Mockito.any(User.class)))
                .thenAnswer(invocation -> {
                    User entity = invocation.getArgument(0);
                    if (passports.contains(entity)) {
                        return userDto;
                    }
                    return new UserDto();
                });

        var result = userController.findAll(pageable);

        result.forEach(resultDto -> assertEquals(userDto, resultDto));
    }

    @Test
    void whenUpdate_andOk() {
        Mockito
                .when(userMapper.toUser(userDto))
                .thenReturn(user.get());

        userController.updateUser(userDto);

        Mockito
                .verify(userService, Mockito.times(1))
                .update(user.get());
    }

    @Test
    void whenUpdate_andNotFound_shouldThrowException() {
        Mockito
                .when(userMapper.toUser(userDto))
                .thenReturn(user.get());
        Mockito
                .doThrow(NotFoundException.class)
                .when(userService)
                .update(user.get());

        assertThrows(NotFoundException.class, () -> userController.updateUser(userDto));

        Mockito
                .verify(userService, Mockito.times(1))
                .update(Mockito.any(User.class));
    }

    @Test
    void whenDelete_andOk() {
        userController.deleteById(UUID_ID);

        Mockito
                .verify(userService, Mockito.times(1))
                .deleteById(UUID_ID);
    }

    @Test
    void whenCreate_andOk() {

        var createdUser = new User();
        var createdUserDto = new UserDto();

        Mockito
                .when(userMapper.toUser(userDto))
                .thenReturn(user.get());
        Mockito
                .when(userService.create(user.get()))
                .thenReturn(createdUser);
        Mockito
                .when(userMapper.toUserDto(createdUser))
                .thenReturn(createdUserDto);

        userController.create(userDto);

        Mockito
                .verify(userService, Mockito.times(1))
                .create(user.get());
    }

    private List<User> generateUser() {
        return Stream
                .generate(User::new)
                .limit(23)
                .collect(Collectors.toList());
    }

    private Pageable createPageable() {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        return PageRequest.of(NUMBER_PAGE, SIZE_PAGE, sort);
    }
}
