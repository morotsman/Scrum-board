package org.github.morotsman.todo.service;

import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.model.user.UserRepository;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(String userName) {
        try{
            return userRepository.makePersistent(getUserInstance(userName));
        }catch(ConstraintViolationException e) {
            throw new ResourceExistException("Could no create the user since it was not unique", e);
        }
    }

    private User getUserInstance(String userName){
        User user = new User();
        user.setName(userName);
        return user;
    }

    @Override
    @Transactional
    public List<User> findUsers() {
        List<User> result = userRepository.findAll();
        Collections.sort(result, new Comparator<User>() {
            @Override
            public int compare(User user, User user1) {
                return user.getName().compareTo(user1.getName());
            }
        }
        );
        return result;
    }

    @Override
    @Transactional
    public User getUser(String userName) {
        List<User> users = userRepository.findByExample(getUserInstance(userName));
        if(users == null || users.size() == 0){
            throw new ResourceNotFoundException("Could not find user: " + userName);
        }
        return users.get(0);
    }

    @Override
    @Transactional
    public void deleteUser(String userName) {
        userRepository.makeTransient(getUser(userName));
    }

    @Resource
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
