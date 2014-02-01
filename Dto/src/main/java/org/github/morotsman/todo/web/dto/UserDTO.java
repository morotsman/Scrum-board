package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "user")
public class UserDTO extends ResourceSupport{

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (userName != null ? !userName.equals(userDTO.userName) : userDTO.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }
}
