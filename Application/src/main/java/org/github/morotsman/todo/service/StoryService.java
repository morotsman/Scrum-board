package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.unit_of_work.Story;

import java.util.List;

public interface StoryService {

    public Story createStory(String teamName, Story story);

    public void deleteStory(String teamName, String storyName);

    public Story getStory(String teamName, String storyName);

    public List<Story> getStories(String teamName, String storyName);

}
