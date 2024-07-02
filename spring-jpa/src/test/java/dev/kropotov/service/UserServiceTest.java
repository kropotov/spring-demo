package dev.kropotov.service;

import dev.kropotov.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private UserDto testUserDto;

    @BeforeEach
    public void create() {
        UserDto userDtoNew = new UserDto()
                .setFio("User User User")
                .setUsername("user1");
        testUserDto = userService.create(userDtoNew);
        userDtoNew.setId(testUserDto.getId());
        assertEquals(testUserDto, userDtoNew);
    }

    @AfterEach
    public void delete() {
        userService.delete(testUserDto.getId());
        assertThrows(Exception.class, () -> userService.readById(testUserDto.getId()));
    }

    @Test
    public void readById() {
        UserDto userDtoNew = userService.readById(testUserDto.getId());
        assertEquals(testUserDto, userDtoNew);
    }

    @Test
    public void update() {
        testUserDto.setUsername("user2");
        UserDto userDtoNew = userService.update(testUserDto.getId(), testUserDto);
        assertEquals(testUserDto, userDtoNew);
    }
}
