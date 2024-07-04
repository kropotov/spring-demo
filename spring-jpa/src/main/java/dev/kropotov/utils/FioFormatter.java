package dev.kropotov.utils;
import dev.kropotov.dto.LoginInputDto;
import dev.kropotov.log.annotations.LogTransformation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@Order(20)
public class FioFormatter implements Formatter<LoginInputDto> {
    @Override
    @LogTransformation
    public void format(LoginInputDto inputDto) {
        inputDto.setName(StringUtils.capitalize(inputDto.getName()));
        inputDto.setSurname(StringUtils.capitalize(inputDto.getSurname()));
        inputDto.setPatronomic(StringUtils.capitalize(inputDto.getPatronomic()));
    }
}
