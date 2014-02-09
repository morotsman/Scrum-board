package org.github.morotsman.todo.controller;

import org.github.morotsman.todo.controller.converter.StoriesToStoriesDTO;
import org.github.morotsman.todo.controller.converter.StoryDTOToStory;
import org.github.morotsman.todo.controller.converter.StoryToStoryDTO;
import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.service.StoryService;
import org.github.morotsman.todo.web.dto.StoriesDTO;
import org.github.morotsman.todo.web.dto.StoryDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/v1")
public class StoryController extends ErrorHandler{


    private StoryService storyService;

    private ConversionService converterService;



    @RequestMapping(value="/team/{teamName}/story/{storyName}", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public StoryDTO createStory(@PathVariable String teamName, @PathVariable String storyName, @RequestBody @Valid StoryDTO storyDTO){
        storyDTO.setName(storyName);
        Story storyToCreate = converterService.convert(storyDTO, Story.class);
        Story createdStory = storyService.createStory(teamName, storyToCreate);
        StoryDTO result = converterService.convert(createdStory, StoryDTO.class);
        result.add(linkTo(methodOn(StoryController.class).getStory(teamName, storyName)).withSelfRel());
        return result;
    }

    @RequestMapping(value="/team/{teamName}/story/{storyName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteStory(@PathVariable String teamName, @PathVariable String storyName){
        storyService.deleteStory(teamName,storyName);
    }

    @RequestMapping(value="/team/{teamName}/story/{storyName}", method = RequestMethod.GET)
    @ResponseBody
    public StoryDTO getStory(@PathVariable String teamName, @PathVariable String storyName){
        StoryDTO result = converterService.convert(storyService.getStory(teamName, storyName),StoryDTO.class);
        result.add(linkTo(methodOn(StoryController.class).getStory(teamName, storyName)).withSelfRel());
        return result;
    }

    @RequestMapping(value="/team/{teamName}/story", method = RequestMethod.GET)
    @ResponseBody
    public StoriesDTO listStories(@PathVariable String teamName){
        StoriesDTO storiesDTO = converterService.convert(storyService.findStories(teamName), StoriesDTO.class);
        storiesDTO.add(linkTo(methodOn(StoryController.class).listStories(teamName)).withSelfRel());
        for(StoryDTO each: storiesDTO.getStories()){
            each.add(linkTo(methodOn(StoryController.class).getStory(teamName, each.getName())).withSelfRel());
        }
        return storiesDTO;

    }


    @Resource
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }


    @Resource(name = "conversionService")
    public void setConverter(ConversionService converter) {
        this.converterService = converter;
    }
}
