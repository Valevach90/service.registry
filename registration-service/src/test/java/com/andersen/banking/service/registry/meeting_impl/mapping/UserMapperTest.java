package com.andersen.banking.service.registry.meeting_impl.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andersen.banking.service.registry.meeting_api.dto.user.UserResponseDto;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest {

    private User user;
    private UserResponseDto userResponseDto;

    @Autowired
    UserMapper userMapper;

    @BeforeEach
    void initData() {
        user = new User();
        user.setId(UUID.fromString("0d4ff469-465e-412b-9737-34d08d227464"));
        user.setFirstName("1");
        user.setLastName("1");
        user.setEmail("1");
        user.setPatronymic("1");
        user.setPhone("1");

        userResponseDto = new UserResponseDto();
        userResponseDto.setId(UUID.fromString("0d4ff469-465e-412b-9737-34d08d227464"));
        userResponseDto.setFirstName("1");
        userResponseDto.setLastName("1");
        userResponseDto.setEmail("1");
        userResponseDto.setPatronymic("1");
        userResponseDto.setPhone("1");
    }

    @Test
    void whenMapEntityToDto_andOk() {
        var result = userMapper.toUserDto(user);
        checkForEquals(user, result);
    }

    @Test
    void whenMapDtoToEntity_andOk() {
        var result = userMapper.toUser(userResponseDto);
        checkForEquals(userResponseDto, result);
    }

    private void checkForEquals(User user, UserResponseDto userResponseDto) {
        assertEquals(user.getId(), userResponseDto.getId());
        assertEquals(user.getFirstName(), userResponseDto.getFirstName());
        assertEquals(user.getLastName(), userResponseDto.getLastName());
        assertEquals(user.getEmail(), userResponseDto.getEmail());
        assertEquals(user.getPatronymic(), userResponseDto.getPatronymic());
        assertEquals(user.getPhone(), userResponseDto.getPhone());
    }

    private void checkForEquals(UserResponseDto userResponseDto, User user) {
        assertEquals(userResponseDto.getId(), user.getId());
        assertEquals(userResponseDto.getFirstName(), user.getFirstName());
        assertEquals(userResponseDto.getLastName(), user.getLastName());
        assertEquals(userResponseDto.getEmail(), user.getEmail());
        assertEquals(userResponseDto.getPatronymic(), user.getPatronymic());
        assertEquals(userResponseDto.getPhone(), user.getPhone());
    }
}
