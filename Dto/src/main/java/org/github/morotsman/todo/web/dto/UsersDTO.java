package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name = "users")
public class UsersDTO extends ResourceSupport{

    private List<UserDTO> users = new ArrayList<UserDTO>();

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUser(List<UserDTO> users) {
        this.users = users;
    }
}
