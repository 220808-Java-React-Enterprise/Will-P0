package com.revature.saltwater.daos;

import com.revature.saltwater.models.User;

import java.util.List;

public interface CrudDAO<T> {

    void save(T obj);
    void update(T obj);
    void delete(String id);
    T getById(String id);
    List<T> getAll();

}
