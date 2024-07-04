package dev.kropotov.log;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Component
public class Logger {
    @SneakyThrows
    public void log(String message, String fileName) {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(message);
        bw.newLine();
        bw.close();
    }
}
