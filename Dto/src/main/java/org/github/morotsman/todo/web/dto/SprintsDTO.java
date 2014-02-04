package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class SprintsDTO extends ResourceSupport{

    private List<SprintDTO> sprints = new ArrayList<SprintDTO>();

    public List<SprintDTO> getSprints() {
        return sprints;
    }

    public void setSprints(List<SprintDTO> sprints) {
        this.sprints = sprints;
    }
}
