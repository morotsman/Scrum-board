package org.github.morotsman.todo.service;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

public class SecurityUserService implements UserDetailsService{

    @Resource
    private UserService userService;

    public SecurityUserService() {
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        org.github.morotsman.todo.model.user.User user = userService.getUser(userName);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createUser(user);
    }

    public void signin(org.github.morotsman.todo.model.user.User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(org.github.morotsman.todo.model.user.User user) {
        return new UsernamePasswordAuthenticationToken(createUser(user), null, Collections.singleton(createAuthority(user)));
    }

    private org.springframework.security.core.userdetails.User createUser(org.github.morotsman.todo.model.user.User user) {
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.singleton(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(org.github.morotsman.todo.model.user.User user) {
        return new SimpleGrantedAuthority(user.getRole());
    }

}
