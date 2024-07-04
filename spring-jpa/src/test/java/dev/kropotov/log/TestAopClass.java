package dev.kropotov.log;

import dev.kropotov.dto.LoginInputDto;
import dev.kropotov.log.annotations.LogTransformation;
import org.springframework.stereotype.Component;

@Component
public class TestAopClass {
    @LogTransformation("testAnnotatedModifier.log")
    public void annotatedModifier(LoginInputDto inputDto, String newName) {
        inputDto.setName(newName);
        System.out.println("annotatedFunction");
    }
}
