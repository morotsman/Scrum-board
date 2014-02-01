package org.github.morotsman.todo.web.dto;


import java.util.ArrayList;
import java.util.List;

public class TeamsDTO {

    private List<TeamDTO> teams = new ArrayList<TeamDTO>();

    public List<TeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDTO> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamsDTO teamsDTO = (TeamsDTO) o;

        if (teams != null ? !teams.equals(teamsDTO.teams) : teamsDTO.teams != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return teams != null ? teams.hashCode() : 0;
    }
}
