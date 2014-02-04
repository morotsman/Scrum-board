package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.sprint.Sprint;

import java.util.List;


public interface SprintService {

    public Sprint createSprint(String teamName, String sprintName);

    public void deleteSprint(String teamName, String sprintName);

    public Sprint getSprint(String teamName, String sprintName);

    public List<Sprint> getSprints(String teamName);

}
