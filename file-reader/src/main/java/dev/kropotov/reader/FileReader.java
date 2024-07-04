package dev.kropotov.reader;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


@Component
public class FileReader {
    @SneakyThrows
    public List<String> scanDir(String path) {
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new FileNotFoundException(path + " is not a directory");
        }
        return Arrays.stream(dir.listFiles())
                .flatMap(this::readFile)
                .toList();
    }

    public Stream<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                result.add(input.nextLine());
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();//TODO: replace
        }
        return result.stream();
    }

}
