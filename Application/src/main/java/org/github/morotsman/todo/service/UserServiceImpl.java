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
    public User createUser(User user) {
        List<User> users = userRepository.findByExample(getUserInstance(user.getName()));
        if(users == null || users.size() == 0){
            return userRepository.makePersistent(user);
        } else{
            User result = users.get(0);
            if(user.getPassword()!= null){
                result.setPassword(user.getPassword());
            }
            result.setEmail(user.getEmail());
            result.setFirstName(user.getFirstName());
            result.setLastName(user.getLastName());
            result.setPhoneNumber(user.getPhoneNumber());
            return result;
        }
    }

    @Override
    public List<User> findUsers() {
        User user = new User();
        user.setRole("ROLE_USER");
        List<User> result = userRepository.findByExample(user);
        for(User each: result){
            System.out.println("************************** " + each.getName() + " " + each.getRole());
        }
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
    public List<User> findUsers(String partOfName) {
        User user = new User();
        user.setRole("ROLE_USER");
        user.setName(partOfName);
        List<User> result = userRepository.findUserLike(user);
        for(User each: result){
            System.out.println("************************** " + each.getName() + " " + each.getRole());
        }
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

    private User getUserInstance(String userName){
        User user = new User();
        user.setName(userName);
        return user;
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
