package dev.kropotov.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginInputDto {
    private String login;
    private String surname;
    private String name;
    private String patronomic;
    private LocalDateTime accessDate;
    private String application;
}
