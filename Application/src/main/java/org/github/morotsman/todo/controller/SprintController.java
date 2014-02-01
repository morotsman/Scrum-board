package org.github.morotsman.todo.controller;


import org.github.morotsman.todo.web.dto.SprintDTO;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1")
public class SprintController {


    @RequestMapping(value="/team/{teamName}/sprint", method = RequestMethod.GET)
    @ResponseBody
    public SprintDTO getSprints(@PathVariable String teamName){
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}", method = RequestMethod.GET)
    @ResponseBody
    public SprintDTO getSprint(@PathVariable String teamName,@PathVariable String sprintName){
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}", method = RequestMethod.PUT)
    @ResponseBody
    public SprintDTO createSprint(@PathVariable String teamName,@PathVariable String sprintName){
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}", method = RequestMethod.DELETE)
    @ResponseBody
    public SprintDTO deleteSprint(@PathVariable String teamName,@PathVariable String sprintName){
        throw new RuntimeException("Not implemented");
    }

}
