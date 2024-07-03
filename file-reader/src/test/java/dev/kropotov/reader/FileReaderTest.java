package dev.kropotov.reader;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {

    @Test
    public void scanDir() {
        FileReader fileReader = new FileReader();
        List<String> strings = fileReader.scanDir("src/test/resources/input");
        List<String> etalon = List.of("user1 Фамилия1 Имя1 Отчество1 2024-07-03T00:23:13 web",
                "user2 Фамилия2 Имя2 Отчество2 2024-07-03T00:23:13 mobile");
        assertEquals(etalon, strings);
    }
}
