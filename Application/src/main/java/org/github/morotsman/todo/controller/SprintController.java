package org.github.morotsman.todo.controller;


import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.service.SprintService;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.github.morotsman.todo.web.dto.SprintDTO;
import org.github.morotsman.todo.web.dto.SprintsDTO;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/v1")
public class SprintController {

    private SprintService sprintService;


    @RequestMapping(value="/team/{teamName}/sprint", method = RequestMethod.GET)
    @ResponseBody
    public SprintsDTO listSprints(@PathVariable String teamName){
        SprintsDTO result = new SprintsDTO();
        for(Sprint sprint:sprintService.getSprints(teamName)){
            result.getSprints().add(toSprintDTO(sprint,teamName));
        }
        result.add(linkTo(methodOn(SprintController.class).listSprints(teamName)).withSelfRel());
        result.add(linkTo(methodOn(TeamController.class).getTeam(teamName)).withRel("team"));
        return result;
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}", method = RequestMethod.GET)
    @ResponseBody
    public SprintDTO getSprint(@PathVariable String teamName,@PathVariable String sprintName){
        return toSprintDTO(sprintService.getSprint(teamName, sprintName), teamName);
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SprintDTO createSprint(@PathVariable String teamName,@PathVariable String sprintName){
        return toSprintDTO(sprintService.createSprint(teamName, sprintName), teamName);
    }

    private SprintDTO toSprintDTO(Sprint sprint, String teamName){
        SprintDTO result = new SprintDTO();
        result.setName(sprint.getName());
        result.add(linkTo(methodOn(SprintController.class).getSprint(teamName, sprint.getName())).withSelfRel());
        result.add(linkTo(methodOn(TeamController.class).getTeam(teamName)).withRel("team"));
        return result;
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteSprint(@PathVariable String teamName,@PathVariable String sprintName){
        sprintService.deleteSprint(teamName,sprintName);
    }

    @Resource
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    //TODO add logger

    @ExceptionHandler(ResourceExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    void handleResourceAlreadyExistsException(Exception exception) {
        //exception.printStackTrace(System.out);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    void handleServiceException(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    void handleResourceNotFoundException(Exception exception) {
        //exception.printStackTrace(System.out);
    }
}
