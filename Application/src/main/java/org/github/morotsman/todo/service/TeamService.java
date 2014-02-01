package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.team.Team;

import java.util.List;

public interface TeamService {


    public List<Team> findTeams();

    public void deleteTeam(String teamName);

    public Team getTeam(String teamName);

    public Team createTeam(String teamName, Team team);
}
