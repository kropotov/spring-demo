package dev.kropotov.loader;

import dev.kropotov.dto.LoginInputDto;
import dev.kropotov.reader.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class LoginLoader implements FileLoader<LoginInputDto> {
    private final FileReader fileReader;

    public List<LoginInputDto> loadDir(String path) {
        List<LoginInputDto> inputDtoList = new ArrayList<>();
        for (String str : fileReader.scanDir(path)) {
            LoginInputDto inputDto = new LoginInputDto();
            int counter = 0;
            for (String strField : str.split(" ")) {
                Field field = inputDto.getClass().getDeclaredFields()[counter++];
                field.setAccessible(true);
                try {
                    if (field.getType() == LocalDateTime.class) {
                         if(!strField.isBlank()) {
                             LocalDateTime dateTime = LocalDateTime.parse(strField);
                             field.set(inputDto, dateTime);
                         }
                    } else {
                        field.set(inputDto, strField);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            inputDtoList.add(inputDto);
        }
        System.out.println("inputDtoList = " + inputDtoList);//TODO: убрать отладку
        return inputDtoList;
    }
}
