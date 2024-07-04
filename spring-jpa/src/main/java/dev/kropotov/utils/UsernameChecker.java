package dev.kropotov.utils;

import dev.kropotov.dto.LoginInputDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class UsernameChecker implements Checker<LoginInputDto>{
    @Override
    public boolean check(LoginInputDto inputDto) {
        return !inputDto.getLogin().isBlank();
    }
}
