package dev.kropotov.service;

import java.util.List;

public interface BaseCRUDService<T> {
    T create(T dto);

    List<T> readAll();

    T readById(Long id);

    T update(Long id, T updatedDto);

    void delete(Long id);
}
