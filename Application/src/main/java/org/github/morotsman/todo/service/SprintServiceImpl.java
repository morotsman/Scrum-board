package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.model.sprint.SprintRepository;
import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class SprintServiceImpl implements SprintService{

    private TeamService teamService;
    private SprintRepository sprintRepository;

    @Transactional
    @Override
    public List<Sprint> getSprints(String teamName) {
        return sortSprints(findSprints(teamName));
    }

    private List<Sprint> findSprints(String teamName){
        Sprint example = new Sprint();
        Team team = teamService.getTeam(teamName);
        example.setTeam(team);
        return  sprintRepository.findByExample(example);
    }

    private List<Sprint> sortSprints(List<Sprint> result) {
        Collections.sort(result, new Comparator<Sprint>() {
            @Override
            public int compare(Sprint sprint1, Sprint sprint2) {
                return sprint1.getName().compareTo(sprint2.getName());
            }
        }
        );
        return result;
    }

    @Transactional
    @Override
    public Sprint createSprint(String teamName, String sprintName) {
        try{
            Sprint sprint = new Sprint();
            sprint.setName(sprintName);
            Team team = teamService.getTeam(teamName);
            sprint.setTeam(team);
            return sprintRepository.makePersistent(sprint);
        }catch(ConstraintViolationException e) {
            throw new ResourceExistException("Could no create the user since it was not unique", e);
        }
    }

    @Override
    public Sprint getSprint(String teamName, String sprintName) {
        return findSprint(teamName,sprintName);
    }

    @Override
    public void deleteSprint(String teamName, String sprintName) {
        Sprint sprint = findSprint(teamName,sprintName);
        sprintRepository.makeTransient(sprint);
    }

    private Sprint findSprint(String teamName, String sprintName){
        Sprint example = new Sprint();
        example.setName(sprintName);
        Team team = teamService.getTeam(teamName);
        example.setTeam(team);
        List<Sprint> sprints =  sprintRepository.findByExample(example);
        if(sprints.size()== 0){
            throw new ResourceNotFoundException("A sprint named " + sprintName + " on the team" + teamName+" don't exists.");
        }
        return sprints.get(0);
    }

    @Resource
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Resource
    public void setSprintRepository(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }
}
