package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

public class TeamDTO extends ResourceSupport {


    private String description;


    public TeamDTO(){}

    public TeamDTO(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TeamDTO teamDTO = (TeamDTO) o;

        if (!description.equals(teamDTO.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
