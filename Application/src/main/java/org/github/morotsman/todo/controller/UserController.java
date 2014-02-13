package org.github.morotsman.todo.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.service.UserService;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.github.morotsman.todo.web.dto.UserDTO;
import org.github.morotsman.todo.web.dto.UsersDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class UserController extends ErrorHandler{



    private UserService userService;


    @RequestMapping(value="/me", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UserDTO getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println("The logged in user is: " + name);
        return toUserDTO(userService.getUser(name),true);
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UsersDTO listUsers() {
        List<User> users = userService.findUsers();
        UsersDTO result = new UsersDTO();
        for(User user: users){
            result.getUsers().add(toUserDTO(user,false));
        }
        result.add(linkTo(methodOn(UserController.class).listUsers()).withSelfRel());
        return result;
    }

    @RequestMapping(value="/user/{userName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    UserDTO createUser(@PathVariable String userName) {
        return toUserDTO(userService.createUser(userName),true);
    }

    private UserDTO toUserDTO(User user, boolean includeMembership){
        UserDTO result = new UserDTO();
        result.setUserName(user.getName());
        result.add(linkTo(methodOn(UserController.class).createUser(user.getName())).withSelfRel());
        result.add(linkTo(methodOn(TeamController.class).findTeams()).withRel("teams"));
        if(includeMembership){  //TODO should we have this link or maybe add another for example http://localhost:8080/todo/services/v1/user/Niklas/membership, investigate when starting to implement GUI.
            for(Membership membership: user.getMemberships()){
                result.add(linkTo(methodOn(MembershipController.class).getMembership(membership.getTeam().getName(),membership.getUser().getName())).withRel("membership"));
            }
        }
        return result;
    }

    @RequestMapping(value="/user/{userName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
    }

    @RequestMapping(value="/user/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UserDTO getUser(@PathVariable String userName) {
        return toUserDTO(userService.getUser(userName),true);
    }



    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
