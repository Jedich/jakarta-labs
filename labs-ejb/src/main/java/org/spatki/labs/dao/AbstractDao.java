package org.spatki.labs.dao;

import jakarta.ejb.Local;
import java.util.Collection;

@Local
public interface AbstractDao<T> {

    T get(Integer id);

    Collection<T> findAll();

    void insert(T entity, boolean generateId);

    void delete(T entity);

    void update(T entity);
}
