package com.andersen.banking.service.registry.meeting_impl.service.local.impl;

import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.repositories.UserRepository;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.service.local.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    UserServiceImplTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Test
    void whenFindAllUsersSuccess() {
        //given
        List<User> users = List.of( new User(1L, "Jack", "Nicholson", "Strong", "jack@mail.com", "555-55-55"),
                new User(2L, "Mickael", "Jackson", "Dancer", "mickael@mail.com", "888-88-88"),
                new User(3L, "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77"));

        //when
        when(this.userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = this.userService.findAll();

        //then
        assertEquals(foundUsers, users);
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    void whenFindByIdSuccess() {
        //given
        User userExpected = new User(1L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        //when
        when(userRepository.findById(any())).thenReturn(Optional.of(userExpected));

        Optional<User> userReturn = userService.findById(1L);

        //then
        assertEquals(Optional.of(userExpected), userReturn);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void whenFindByIdUserNotFound(){
        //given
        long id = 10L;

        //when
        Throwable throwable = assertThrows(NotFoundException.class,
                () -> userService.findById(id));

        //then
        assertEquals(NotFoundException.class, throwable.getClass());
    }

    @Test
    void whenSaveUserSuccess() {
        //given
        User userExpected = new User(1L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        //when
        when(userRepository.save(any())).thenReturn(userExpected);
        Optional<User> userActual = userService.saveUser(userExpected);

        //given
        assertEquals(Optional.of(userExpected), userActual);
        verify(userRepository, times(1)).save(userExpected);
    }

    @Test
    void whenUpdateUserSuccess() {
        //given
        long id = 1L;
        User userOld = new User(id,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");
        User userNew = new User(id,"James", "Bond", "Agent", "bond@mail.com", "888-88-88");

        //when
        when(userRepository.findById(id)).thenReturn(Optional.of(userOld));
        when(userRepository.save(any())).thenReturn(userNew);
        Optional<User> userActual = userService.updateUser(id, userNew);

        //then
        assertEquals(Optional.of(userNew), userActual);
        verify(userRepository, times(1)).save(userNew);


    }

    @Test
    void whenDeleteUserSuccess() {
        //given
        User user = new User(11L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        //when
        when(userRepository.save(any())).thenReturn(user);
        boolean isDeleted = userService.deleteUser(user.getId());

        //then
        assertTrue(isDeleted);

    }

    @Test
    void whenDeletedUserByIdWithException(){
        //given
        Long id = 1L;

        //when
        doThrow(RuntimeException.class).when(userRepository).deleteById(id);

        //then
        assertThrows(RuntimeException.class, () -> userService.deleteUser(id));

    }
}