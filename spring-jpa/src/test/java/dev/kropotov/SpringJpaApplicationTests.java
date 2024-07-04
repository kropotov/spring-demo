package dev.kropotov;

import dev.kropotov.dto.LoginInputDto;
import dev.kropotov.log.TestAopClass;
import dev.kropotov.reader.FileReader;
import dev.kropotov.utils.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class SpringJpaApplicationTests {
    @Autowired
    private List<Checker> checkers;
    @Autowired
    private UsernameChecker usernameChecker;    //Ordered first
    @Autowired
    private DateChecker dateChecker;    //Ordered last


    @Autowired
    private List<Formatter> formatters;
    @Autowired
    private AppTypeFormatter appTypeFormatter;    //Ordered first
    @Autowired
    private FioFormatter fioFormatter;    //Ordered last

    @Autowired
    TestAopClass testAopClass;
    @Autowired
    FileReader fileReader;

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

    @Test
    void contextLoads() {
    }

    @Test
    void testFormattersOrder() {
        List etalonCheckers = new ArrayList<>();
        etalonCheckers.add(usernameChecker);//Ordered first
        etalonCheckers.add(dateChecker);//Ordered last
        assertEquals(etalonCheckers, checkers);
    }

    @Test
    void testCheckersOrder() {
        List etalonFormatters = new ArrayList<>();
        etalonFormatters.add(appTypeFormatter);//Ordered first
        etalonFormatters.add(fioFormatter);//Ordered last
        assertEquals(etalonFormatters, formatters);
    }

    @Test
    void logTransformationTest() {
        String name = "name1", newName = "name2";
        LoginInputDto loginInputDto = new LoginInputDto();
        loginInputDto.setName(name);
        testAopClass.annotatedModifier(loginInputDto, newName);
        assertEquals(loginInputDto.getName(), newName);
        File logFile = new File("testAnnotatedModifier.log");
        assertTrue(fileReader.readFile(logFile)
                .peek(System.out::println)
                .anyMatch(str -> str.contains(name) && str.contains(newName)));
        logFile.delete();
    }
}
