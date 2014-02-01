package org.github.morotsman.todo.service;

import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.team.TeamRepository;
import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;


    @Transactional
    @Override
    public List<Team> findTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteTeam(String teamName) {
        System.out.println("Delete:" + teamName);
        Team team = findUniqueTeam(teamName);
        teamRepository.makeTransient(team);
    }

    private Team findUniqueTeam(String teamName){
        List<Team> teams = findByName(teamName);
        if(teams.size() == 0){
            throw new ResourceNotFoundException("A team named " + teamName + " don't exists.");
        }
        return teams.get(0);
    }

    @Transactional
    @Override
    public Team createTeam(String teamName, Team team) {
        List<Team> teams = findByName(teamName);
        if(teams.size() == 0){
            System.out.println("create: " + teamName);
            Team teamToCreate = new Team();
            teamToCreate.setName(teamName);
            teamToCreate.setDescription(team.getDescription());
            return teamRepository.makePersistent(teamToCreate);
        }else{
            System.out.println("modify: " + teamName);
            Team result = teams.get(0);
            result.setDescription(team.getDescription());
            return result;
        }
    }

    private List<Team> findByName(String teamName){
        Team example = new Team();
        example.setName(teamName);

        List<Team> result =teamRepository.findByExample(example, "description");

        Collections.sort(result, new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2) {
                return team1.getName().compareTo(team2.getName());
            }
        }
        );
        return result;
    }

    @Transactional
    @Override
    public Team getTeam(String teamName) {
        return findUniqueTeam(teamName);
    }

    @Resource
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

}
