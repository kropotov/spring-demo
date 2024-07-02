package dev.kropotov.service;

import dev.kropotov.dto.LoginDto;
import dev.kropotov.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LoginServiceTest {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    private LoginDto testLoginDto;
    private UserDto testUserDto;

    @BeforeEach
    public void create() {
        testUserDto = new UserDto()
                .setFio("User User User")
                .setUsername("user1");

        LoginDto loginDtoNew = new LoginDto()
                .setAccessDate(LocalDateTime.now())
                .setApplication("web")
                .setUser(testUserDto);

        testLoginDto = loginService.create(loginDtoNew);
        loginDtoNew.setId(testLoginDto.getId());
        testUserDto = testLoginDto.getUser();
        loginDtoNew.setUser(testUserDto);

        assertEquals(testLoginDto, loginDtoNew);
    }

    @AfterEach
    public void delete() {
        loginService.delete(testLoginDto.getId());
        userService.delete(testUserDto.getId());
        assertThrows(Exception.class, () -> loginService.readById(testLoginDto.getId()));
    }

    @Test
    public void readById() {
        LoginDto loginDtoNew = loginService.readById(testLoginDto.getId());
        assertEquals(testLoginDto, loginDtoNew);
    }

    @Test
    public void update() {
        testLoginDto.setApplication("mobile");

        LoginDto loginDtoNew = loginService.update(testLoginDto.getId(), testLoginDto);
        assertEquals(testLoginDto, loginDtoNew);
    }
}
