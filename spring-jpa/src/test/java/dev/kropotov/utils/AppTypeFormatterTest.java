package dev.kropotov.utils;

import dev.kropotov.dto.LoginInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTypeFormatterTest {

    @Test
    public void format() {
        AppTypeFormatter appTypeFormatter = new AppTypeFormatter();
        LoginInputDto inputDto = new LoginInputDto();
        inputDto.setApplication("web");
        appTypeFormatter.format(inputDto);
        assertEquals(inputDto.getApplication(), "web");

        inputDto.setApplication("Desktop");
        appTypeFormatter.format(inputDto);
        assertEquals(inputDto.getApplication(), "other: Desktop");
    }
}
