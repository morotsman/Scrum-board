package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultDataService {


    @Autowired
    public DefaultDataService(UserService userService){
        User user = new User();
        user.setName("user");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        userService.createUser(user);
    }





}
