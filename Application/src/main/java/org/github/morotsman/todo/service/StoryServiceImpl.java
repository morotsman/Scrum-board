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
import java.util.List;

@Service
@Transactional
public class StoryServiceImpl implements StoryService{

    private StoryRepository storyRepository;
    private TeamService teamService;


    @Override
    public Story createStory(String teamName, Story story) {
        try{
            Team team = teamService.getTeam(teamName);
            story.addTeam(team);
            return storyRepository.makePersistent(story);
        }catch(ConstraintViolationException e) {
            throw new ResourceExistException("Could no create the user since it was not unique", e);
        }
    }

    @Override
    public void deleteStory(String teamName, String storyName) {
        storyRepository.makeTransient(findStory(teamName,storyName));
    }

    private Story findStory(String teamName, String storyName){
        Story example = new Story();
        example.setName(storyName);
        Team team = teamService.getTeam(teamName);
        example.setTeam(team);
        List<Story> stories =  storyRepository.findByExample(example);
        if(stories.size()== 0){
            throw new ResourceNotFoundException("A story named " + storyName + " on the team" + teamName+" don't exists.");
        }
        return stories.get(0);
    }

    @Override
    public Story getStory(String teamName, String storyName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Story> getStories(String teamName, String storyName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
