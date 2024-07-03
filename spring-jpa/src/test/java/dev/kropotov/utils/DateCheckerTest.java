package dev.kropotov.utils;

import dev.kropotov.dto.LoginInputDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateCheckerTest {
    @Test
    public void check() {
        DateChecker dateFormatter = new DateChecker();
        LoginInputDto inputDto = new LoginInputDto();
        assertFalse(dateFormatter.check(inputDto));
        LocalDateTime now = LocalDateTime.now();
        inputDto.setAccessDate(now);
        assertTrue(dateFormatter.check(inputDto));
    }
}
