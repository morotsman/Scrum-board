package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

public class StoryDTO extends ResourceSupport {


    private String name;

    private String description;

    private Long estimate;

    public StoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEstimate() {
        return estimate;
    }

    public void setEstimate(Long estimate) {
        this.estimate = estimate;
    }
}
