package org.github.morotsman.todo.model.repository;

import java.io.Serializable;
import java.util.List;

//Generic repository as described in the book Java persistence with Hibernate page 709-714
public interface GenericDao<T, ID extends Serializable> {

    T findById(ID id,boolean lock);

    List<T> findAll();

    List<T> findByExample(T exampleInstance, String... excludeProperties);

    T makePersistent(T entity);

    void makeTransient(T entity);

}
