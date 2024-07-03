package dev.kropotov.importer;

import dev.kropotov.dto.LoginInputDto;
import dev.kropotov.dto.LoginDto;
import dev.kropotov.dto.UserDto;
import dev.kropotov.loader.FileLoader;
import dev.kropotov.service.LoginService;
import dev.kropotov.utils.Checker;
import dev.kropotov.utils.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginImport implements ApplicationRunner {
    private final LoginService loginService;
    private final FileLoader<LoginInputDto> fileLoader;
    private final List<Checker<LoginInputDto>> checkers;
    private final List<Formatter<LoginInputDto>> formatters;


    @Value("${application.path}")
    private String path;

    @Override
    public void run(ApplicationArguments args) {
        fileLoader.loadDir(path).forEach(this::saveLogin);
    }

    public LoginDto saveLogin(LoginInputDto inputDto) {
        boolean isError = checkers.stream().anyMatch(checker -> !checker.check(inputDto));

        if (isError) {
            return null;
        }

        formatters.forEach(formatter -> formatter.format(inputDto));

        UserDto userDto = new UserDto();
        userDto.setUsername(inputDto.getLogin());

        userDto.setFio(String.format("%s %s %s",
                inputDto.getSurname(),
                inputDto.getName(),
                inputDto.getPatronomic()));

        LoginDto loginDto = new LoginDto()
                .setApplication(inputDto.getApplication())
                .setAccessDate(inputDto.getAccessDate())
                .setUser(userDto);

        return loginService.create(loginDto);
    }
}
