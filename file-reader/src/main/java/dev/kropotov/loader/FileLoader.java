package dev.kropotov.loader;

import java.util.List;


public interface FileLoader<T> {
    List<T> loadDir(String path);
}
