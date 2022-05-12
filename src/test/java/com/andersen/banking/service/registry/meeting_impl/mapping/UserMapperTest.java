package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void whenMapToAUserDto() {
        User user = new User(1L, "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77");

        UserDto userDto = userMapper.toUserDto(user);
        assertNotNull(userDto);

        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getPatronymic(), userDto.getPatronymic());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPhone(), userDto.getPhone());

    }

    @Test
    void whenMapToListUserDto(){
        List<User> users = List.of( new User(1L, "Jack", "Nicholson", "Strong", "jack@mail.com", "555-55-55"),
                new User(2L, "Mickael", "Jackson", "Dancer", "mickael@mail.com", "888-88-88"),
                new User(3L, "Chuck", "Norris", "Fighter", "chuck@mail.com", "777-77-77"));

       List<UserDto> usersListDto = userMapper.toListDtoUsers(users);

        assertNotNull(usersListDto);
        assertFalse(usersListDto.isEmpty());
        assertEquals(users.size(), usersListDto.size());
    }
}