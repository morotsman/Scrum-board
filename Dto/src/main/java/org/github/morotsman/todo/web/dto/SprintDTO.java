package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

public class SprintDTO extends ResourceSupport {

    private String name;

    public SprintDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
