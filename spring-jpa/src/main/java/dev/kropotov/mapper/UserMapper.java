package dev.kropotov.mapper;

import dev.kropotov.dto.UserDto;
import dev.kropotov.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}