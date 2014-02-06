package org.github.morotsman.todo.controller;

import org.github.morotsman.todo.web.dto.StoriesDTO;
import org.github.morotsman.todo.web.dto.StoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1")
public class SprintBacklogController extends ErrorHandler{



    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}/backlog", method = RequestMethod.GET)
    @ResponseBody
    public StoriesDTO listSprintBacklog(@PathVariable String teamName, @PathVariable String sprintName){
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}/backlog/{storyName}", method = RequestMethod.PUT)
    @ResponseBody
    public StoriesDTO addToSprintBacklog(@PathVariable String teamName, @PathVariable String sprintName, @PathVariable String storyName){
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value="/team/{teamName}/sprint/{sprintName}/backlog/{storyName}", method = RequestMethod.DELETE)
    @ResponseBody
    public StoriesDTO removeFromSprintBacklog(@PathVariable String teamName, @PathVariable String sprintName, @PathVariable String storyName){
        throw new RuntimeException("Not implemented");
    }




}
