package uz.pdp.service;

import java.util.List;
import java.util.function.Predicate;

public interface BaseService<T> {
    T get(String id);

    boolean update(T t);

    void create(T t);

    boolean delete(String id);

    List<T> getListByFilter(Predicate<T> filter);
}
