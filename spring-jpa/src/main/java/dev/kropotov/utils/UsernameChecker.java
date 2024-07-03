package dev.kropotov.utils;

import dev.kropotov.dto.LoginInputDto;
import org.springframework.stereotype.Component;

@Component
public class UsernameChecker implements Checker<LoginInputDto>{
    @Override
    public boolean check(LoginInputDto inputDto) {
        return !inputDto.getLogin().isBlank();
    }
}
