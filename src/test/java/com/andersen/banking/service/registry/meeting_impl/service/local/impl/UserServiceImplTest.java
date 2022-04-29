package com.andersen.banking.service.registry.meeting_impl.service.local.impl;

import com.andersen.banking.service.registry.meeting_db.entities.UserEntity;
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
        List<UserEntity> users = List.of( new UserEntity(1L, "Jack", "Nicholson", "Strong", "jack@mail.com", "555-55-55"),
                new UserEntity(2L, "Mickael", "Jackson", "Dancer", "mickael@mail.com", "888-88-88"),
                new UserEntity(3L, "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77"));

        //when
        when(this.userRepository.findAll()).thenReturn(users);

        List<UserEntity> foundUsers = this.userService.findAll();

        //then
        assertEquals(foundUsers, users);
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    void whenFindByIdSuccess() {
        //given
        UserEntity userExpected = new UserEntity(1L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        //when
        when(userRepository.findById(any())).thenReturn(Optional.of(userExpected));

        Optional<UserEntity> userReturn = userService.findById(1L);

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
        UserEntity userExpected = new UserEntity(1L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        //when
        when(userRepository.save(any())).thenReturn(userExpected);
        Optional<UserEntity> userActual = userService.saveUser(userExpected);

        //given
        assertEquals(Optional.of(userExpected), userActual);
        verify(userRepository, times(1)).save(userExpected);
    }

    @Test
    void whenUpdateUserSuccess() {
        //given
        long id = 1L;
        UserEntity userOld = new UserEntity(id,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");
        UserEntity userNew = new UserEntity(id,"James", "Bond", "Agent", "bond@mail.com", "888-88-88");

        //when
        when(userRepository.findById(id)).thenReturn(Optional.of(userOld));
        when(userRepository.save(any())).thenReturn(userNew);
        Optional<UserEntity> userActual = userService.updateUser(id, userNew);

        //then
        assertEquals(Optional.of(userNew), userActual);
        verify(userRepository, times(1)).save(userNew);


    }

    @Test
    void whenDeleteUserSuccess() {
        //given
        UserEntity user = new UserEntity(11L,"Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

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