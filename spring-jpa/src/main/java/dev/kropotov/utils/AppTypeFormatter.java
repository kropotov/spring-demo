package dev.kropotov.utils;

import dev.kropotov.dto.LoginInputDto;
import org.springframework.stereotype.Component;

@Component
public class AppTypeFormatter implements Formatter<LoginInputDto> {
    @Override
    public void format(LoginInputDto inputDto) {
        if ("web".equals(inputDto.getApplication()) || "mobile".equals(inputDto.getApplication())) {
            return;
        }
        inputDto.setApplication("other: " + inputDto.getApplication());
    }
}
