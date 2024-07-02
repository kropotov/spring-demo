package dev.kropotov.service;

import dev.kropotov.dto.LoginInputDto;
import dev.kropotov.dto.LoginDto;
import dev.kropotov.dto.UserDto;
import dev.kropotov.loader.FileLoader;
import dev.kropotov.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginImportService {
    private final LoginService loginService;
    private final FileLoader<LoginInputDto> fileLoader;

    @Value("${application.path}")
    private String path;

    public void load() {
        fileLoader.loadDir(path).forEach(this::saveLogin);
    }

    public LoginDto saveLogin(LoginInputDto inputDto) {
        UserDto userDto = new UserDto();
        userDto.setUsername(inputDto.getLogin());
        userDto.setFio(Utils.formatFio(
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
