package dev.kropotov.service;

import dev.kropotov.dto.LoginDto;
import dev.kropotov.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
public class LoginServiceTest {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    private LoginDto testLoginDto;
    private UserDto testUserDto;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("prop")
            .withUsername("postgres")
            .withPassword("pass")
            .withExposedPorts(5432)
            .withReuse(true);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgres.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "pass");
    }


    @BeforeEach
    public void create() {
        testUserDto = new UserDto()
                .setFio("User User User")
                .setUsername("user1");

        LoginDto loginDtoNew = new LoginDto()
                .setAccessDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
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
