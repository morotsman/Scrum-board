package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.user.User;

import java.util.List;

public interface UserService {

    public User createUser(String userName);

    public List<User> findUsers();

    public void deleteUser(String userName);

    public User getUser(String userName);

}
