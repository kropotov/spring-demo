package dev.kropotov.utils;

import dev.kropotov.dto.LoginInputDto;
import org.springframework.stereotype.Component;

@Component
public class DateChecker implements Checker<LoginInputDto> {
    @Override
    public boolean check(LoginInputDto inputDto) {
        return (inputDto.getAccessDate() != null);
    }
}
