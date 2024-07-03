package dev.kropotov.utils;
import dev.kropotov.dto.LoginInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FioFormatterTest {

    @Test
    public void format() {
        FioFormatter fioFormatter = new FioFormatter();
        LoginInputDto inputDto = new LoginInputDto();
        inputDto.setName("vasya");
        fioFormatter.format(inputDto);
        assertEquals(inputDto.getName(), "Vasya");
    }
}
