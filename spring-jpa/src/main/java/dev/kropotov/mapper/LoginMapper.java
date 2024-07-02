package dev.kropotov.mapper;

import dev.kropotov.dto.LoginDto;
import dev.kropotov.entity.Login;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface LoginMapper {
    LoginDto toDto(Login login);

    Login toEntity(LoginDto loginDto);
}