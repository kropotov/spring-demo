package dev.kropotov.service;

import dev.kropotov.dto.UserDto;
import dev.kropotov.entity.User;
import dev.kropotov.mapper.UserMapper;
import dev.kropotov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements BaseCRUDService<UserDto> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserDto dto) {
        User user = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> readAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto readById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow();
    }

    @Override
    public UserDto update(Long id, UserDto updatedDto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFio(updatedDto.getFio());
        user.setUsername(updatedDto.getUsername());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
