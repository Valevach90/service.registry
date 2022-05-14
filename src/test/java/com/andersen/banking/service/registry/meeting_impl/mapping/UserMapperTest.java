package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest {

    private User user;
    private UserDto userDto;

    @Autowired
    UserMapper userMapper;

    @BeforeEach
    void initData() {
        user = new User();
        user.setId(1L);
        user.setFirstName("1");
        user.setLastName("1");
        user.setEmail("1");
        user.setPatronymic("1");
        user.setPhone("1");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("1");
        userDto.setLastName("1");
        userDto.setEmail("1");
        userDto.setPatronymic("1");
        userDto.setPhone("1");
    }

    @Test
    void whenMapEntityToDto_andOk() {
        var result = userMapper.toUserDto(user);
        checkForEquals(user, result);
    }

    @Test
    void whenMapDtoToEntity_andOk() {
        var result = userMapper.toUser(userDto);
        checkForEquals(userDto, result);
    }

    private void checkForEquals(User user, UserDto userDto) {
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPatronymic(), userDto.getPatronymic());
        assertEquals(user.getPhone(), userDto.getPhone());
    }

    private void checkForEquals(UserDto userDto, User user) {
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPatronymic(), user.getPatronymic());
        assertEquals(userDto.getPhone(), user.getPhone());
    }
}
