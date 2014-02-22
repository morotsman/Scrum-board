package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.sprint.Sprint;

import java.util.List;
import java.util.Set;


public interface SprintService {

    public Sprint createSprint(String teamName, String sprintName);

    public void deleteSprint(String teamName, String sprintName);

    public Sprint getSprint(String teamName, String sprintName);

    public Set<Sprint> getSprints(String teamName);

}
