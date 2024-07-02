package dev.kropotov.service;

import dev.kropotov.dto.LoginDto;
import dev.kropotov.entity.Login;
import dev.kropotov.mapper.LoginMapper;
import dev.kropotov.repository.LoginRepository;
import dev.kropotov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService implements BaseCRUDService<LoginDto> {
    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;
    private final UserRepository userRepository;

    @Override
    public LoginDto create(LoginDto dto) {
        Login login = loginRepository.save(loginMapper.toEntity(dto));
        return loginMapper.toDto(login);
    }

    @Override
    public List<LoginDto> readAll() {
        return loginRepository.findAll().stream().map(loginMapper::toDto).toList();
    }

    @Override
    public LoginDto readById(Long id) {
        return loginRepository.findById(id).map(loginMapper::toDto).orElseThrow();
    }

    @Override
    public LoginDto update(Long id, LoginDto updatedDto) {
        Login login = loginRepository.findById(id).orElseThrow();
        login.setAccessDate(updatedDto.getAccessDate());
        login.setApplication(updatedDto.getApplication());
        login.setUser(userRepository.findById(updatedDto.getUser().getId()).orElseThrow());
        return loginMapper.toDto(loginRepository.save(login));
    }

    @Override
    public void delete(Long id) {
        loginRepository.deleteById(id);
    }
}
