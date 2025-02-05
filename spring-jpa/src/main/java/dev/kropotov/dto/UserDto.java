package dev.kropotov.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UserDto {
    private Long id;
    private String username;
    private String fio;
}
