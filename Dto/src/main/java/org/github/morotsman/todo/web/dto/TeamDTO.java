package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

public class TeamDTO extends ResourceSupport {


    private String description;
    private String teamName;


    public TeamDTO(){}

    public TeamDTO(String description, String teamName) {
        this.description = description;
        this.teamName = teamName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
