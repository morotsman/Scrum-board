package org.github.morotsman.todo.service;

import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.model.unit_of_work.StoryRepository;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StoryServiceImpl implements StoryService{

    private StoryRepository storyRepository;
    private TeamService teamService;


    @Override
    public Story createStory(String teamName, Story story) {
        List<Story> stories =  getStories(teamName,story.getName());
        if(stories.size() == 0){
            Team team = teamService.getTeam(teamName);
            story.addTeam(team);
            return storyRepository.makePersistent(story);
        } else{
            Story storyToUpdate = stories.get(0);
            storyToUpdate.setDescription(story.getDescription());
            storyToUpdate.setEstimate(story.getEstimate());
            storyToUpdate.setBug(story.isBug());
            return storyToUpdate;
        }
    }

    @Override
    public void deleteStory(String teamName, String storyName) {
        List<Story> stories =  getStories(teamName,storyName);
        if(stories.size()== 0){
            throw new ResourceNotFoundException("A story named " + storyName + " on the team" + teamName+" don't exists.");
        }
        storyRepository.makeTransient(stories.get(0));
    }

    private List<Story> getStories(String teamName, String storyName){
        Story example = new Story();
        example.setName(storyName);
        Team team = teamService.getTeam(teamName);
        example.setTeam(team);
        return storyRepository.findByExample(example);
    }

    @Override
    public Story getStory(String teamName, String storyName) {
        List<Story> stories =  getStories(teamName,storyName);
        if(stories.size()== 0){
            throw new ResourceNotFoundException("A story named " + storyName + " on the team" + teamName+" don't exists.");
        }
        return stories.get(0);
    }

    @Override
    public Set<Story> findStories(String teamName) {
        return teamService.getTeam(teamName).getBacklog();
    }


    @Resource
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Resource
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
