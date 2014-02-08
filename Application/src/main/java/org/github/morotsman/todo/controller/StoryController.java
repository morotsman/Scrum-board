package org.github.morotsman.todo.controller;

import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.service.StoryService;
import org.github.morotsman.todo.web.dto.StoriesDTO;
import org.github.morotsman.todo.web.dto.StoryDTO;
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

    @RequestMapping(value="/team/{teamName}/story/{storyName}", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public StoryDTO createStory(@PathVariable String teamName, @PathVariable String storyName, @RequestBody @Valid StoryDTO storyDTO){
        System.out.println("story: " + storyName);
        return toStoryDTO(storyService.createStory(teamName,fromStoryDTO(storyName,storyDTO)),teamName);

    }

    private StoryDTO toStoryDTO(Story story,String teamName){
        StoryDTO result = new StoryDTO();
        result.setDescription(story.getDescription());
        result.setEstimate(story.getEstimate());
        result.setName(story.getName());
        result.add(linkTo(methodOn(StoryController.class).getStory(teamName, story.getName())).withSelfRel());
        return result;
    }

    private Story fromStoryDTO(String storyName, StoryDTO storyDTO){
        Story result = new Story();
        result.setName(storyName);
        result.setEstimate(storyDTO.getEstimate());
        result.setDescription(storyDTO.getDescription());
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
        return toStoryDTO(storyService.getStory(teamName,storyName),teamName);
    }

    @RequestMapping(value="/team/{teamName}/story", method = RequestMethod.GET)
    @ResponseBody
    public StoriesDTO listStories(@PathVariable String teamName){
        List<Story> stories = storyService.findStories(teamName);
        return toStoriesDTO(teamName, stories);

    }

    private StoriesDTO toStoriesDTO(String teamName, List<Story> stories) {
        StoriesDTO result = new StoriesDTO();
        for(Story each: stories){
            result.getStories().add(toStoryDTO(each,teamName));
        }
        result.add(linkTo(methodOn(StoryController.class).listStories(teamName)).withSelfRel());
        return result;
    }

    @Resource
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }
}
