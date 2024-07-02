package dev.kropotov.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class FileReader {
    public static List<String> scanDir(String path) {
        return Arrays.stream(new File(path).listFiles())
                .flatMap(FileReader::readFile)
                .toList();
    }

    public static Stream<String> readFile(File file) {
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
        System.out.println(result);
        return result.stream();
    }

}
