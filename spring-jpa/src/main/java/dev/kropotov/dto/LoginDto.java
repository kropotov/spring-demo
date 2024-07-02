package dev.kropotov.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class LoginDto {
    private Long id;
    private String application;
    private LocalDateTime accessDate;
    private UserDto user;
}
