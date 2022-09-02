package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import com.andersen.banking.service.registry.meeting_impl.service.impl.UserServiceImpl;
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

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceImplTest {
    private static final Long ID = 23L;
    private static final UUID UUID_ID = UUID.fromString("0d4ff469-465e-412b-9737-34d08d227464");
    private static final Integer NUMBER_PAGE = 0;
    private static final Integer SIZE_PAGE = 10;
    private static final String SORT_FIELD = "id";

    private Optional<User> user;
    private User userToUpdate;

    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @BeforeEach
    void initData() {
        user = Optional.of(new User());
        userToUpdate = new User();
    }

    @Test
    void whenFindById_andOk() {
        Mockito
                .when(userRepository.findById(UUID_ID))
                .thenReturn(user);

        var result = userService.findById(UUID_ID);

        assertEquals(user, result);
    }

    @Test
    void whenFindAll_andOk() {
        List<User> passports = generateUsers();
        Pageable pageable = createPageable();
        Page<User> page = new PageImpl<>(passports, pageable, SIZE_PAGE);

        Mockito
                .when(userRepository.findAll(pageable))
                .thenReturn(page);

        Page<User> result = userRepository.findAll(pageable);

        assertEquals(page, result);
    }

    @Test
    void whenUpdate_andOk() {
        user.get().setId(UUID_ID);
        userToUpdate.setId(UUID_ID);

        Mockito
                .when(userRepository.findById(userToUpdate.getId()))
                .thenReturn(user);

        userService.update(userToUpdate);

        assertEquals(user.get().getId(), userToUpdate.getId());

        Mockito
                .verify(userRepository, Mockito.times(1))
                .save(userToUpdate);
    }

    @Test
    void whenUpdate_andNotFound_shouldThrowException() {
        userToUpdate.setId(UUID_ID);

        Mockito
                .when(userRepository.findById(UUID_ID))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.update(userToUpdate));
    }

    @Test
    void whenDeleteById_andOk() {
        Mockito
                .when(userRepository.findById(UUID_ID))
                .thenReturn(user);

        userService.deleteById(UUID_ID);

        Mockito
                .verify(userRepository, Mockito.times(1))
                .deleteById(UUID_ID);
    }

    @Test
    void whenDeleteById_andNotFound_shouldThrowException() {
        assertThrows(NotFoundException.class, () -> userService.deleteById(UUID_ID));

        Mockito
                .verify(userRepository, Mockito.times(0))
                .deleteById(UUID_ID);
    }

    @Test
    void whenCreate_andOk() {

        Mockito
                .when(userService.findById(UUID_ID))
                .thenReturn(user);
        Mockito
                .when(userRepository.save(user.get()))
                .thenReturn(user.get());

        var result = userService.create(user.get());

        assertEquals(user.get(), result);
    }

    private List<User> generateUsers() {
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