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


}
