package org.github.morotsman.todo.controller;

import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.service.TeamService;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.github.morotsman.todo.web.dto.TeamsDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/v1")
public class TeamController extends ErrorHandler{

    private TeamService teamService;


    private TeamDTO toTeamDTO(Team team, boolean withMembership) {
        TeamDTO result = new TeamDTO();
        result.setDescription(team.getDescription());
        result.setTeamName(team.getName());
        result.add(linkTo(methodOn(TeamController.class).getTeam(team.getName())).withSelfRel());
        result.add(linkTo(methodOn(MembershipController.class).listMemberships(team.getName())).withRel("membership"));
        result.add(linkTo(methodOn(SprintController.class).listSprints(team.getName())).withRel("sprint"));
        return result;
    }

    private Team fromTeamDTO(TeamDTO teamDTO){
        Team result = new Team();
        result.setDescription(teamDTO.getDescription());
        return result;
    }

    @RequestMapping(value="/team", method = RequestMethod.GET)
    public @ResponseBody
    TeamsDTO findTeams() {
        TeamsDTO result = new TeamsDTO();
        for(Team each: teamService.findTeams()){
            result.getTeams().add(toTeamDTO(each,false));
        }
        return result;
    }

    @RequestMapping(value="/team/{teamName}", method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteTeam(@PathVariable String teamName) {
        teamService.deleteTeam(teamName);
    }

    @RequestMapping(value="/team/{teamName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    TeamDTO createTeam(@PathVariable String teamName, @RequestBody @Valid TeamDTO teamDTO) {
        Team team = fromTeamDTO(teamDTO);
        return toTeamDTO(teamService.createTeam(teamName, team),true);
    }

    @RequestMapping(value="/team/{teamName}", method = RequestMethod.GET)
    public @ResponseBody
    TeamDTO getTeam(@PathVariable String teamName) {
        Team team = teamService.getTeam(teamName);
        return toTeamDTO(team,true);
    }



    @Resource
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
