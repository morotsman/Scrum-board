package org.github.morotsman.todo.controller;

import org.github.morotsman.todo.web.dto.StoriesDTO;
import org.github.morotsman.todo.web.dto.StoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1")
public class TeamBacklogController extends ErrorHandler{


    @RequestMapping(value="/team/{teamName}/backlog", method = RequestMethod.GET)
    @ResponseBody
    public StoriesDTO listTeamBacklog(@PathVariable String teamName){
        throw new RuntimeException("Not implemented");
    }



}
