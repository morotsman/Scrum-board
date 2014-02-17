package org.github.morotsman.todo.model.user;

import org.github.morotsman.todo.model.repository.GenericDao;

import java.util.List;


public interface UserRepository extends GenericDao<User,Long> {

    public List<User> findUserLike(User exampleInstance, String... excludeProperties);

}


