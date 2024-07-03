package dev.kropotov.service;

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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
public class UserServiceTest {
    @Autowired
    private UserService userService;

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
