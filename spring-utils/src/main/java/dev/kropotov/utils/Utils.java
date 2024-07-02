package dev.kropotov.utils;
import org.springframework.util.StringUtils;


public class Utils {
    public static String formatFio(String surname, String name, String patronymic) {
        return String.format("%s %s %s",
                StringUtils.capitalize(surname),
                StringUtils.capitalize(name),
                StringUtils.capitalize(patronymic));
    }
}
