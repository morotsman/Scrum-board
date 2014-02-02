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
public class TeamController {

    private TeamService teamService;


    private TeamDTO toTeamDTO(Team team, boolean withMembership) {
        TeamDTO result = new TeamDTO();
        result.setDescription(team.getDescription());
        result.add(linkTo(methodOn(TeamController.class).getTeam(team.getName())).withSelfRel());
        if(withMembership){
            for(Membership membership: team.getMemberships()){
                result.add(linkTo(methodOn(MembershipController.class).getMembership(membership.getTeam().getName(),membership.getUser().getName())).withRel("membership"));
            }
        }
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

    @ExceptionHandler(ResourceExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    void handleResourceAlreadyExistsException(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    void handleServiceException(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @ExceptionHandler({ResourceNotFoundException.class,ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    void handleResourceNotFoundException(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @Resource
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}