package ru.job4j.persistance;

import java.util.List;

public interface DAO<T> {

    int create(T obj);

    List<T> read();

    void update(int id, T newObj);

    void delete(T obj);

    T getById(int id);

    List<T> getByQuery(String query);

}
