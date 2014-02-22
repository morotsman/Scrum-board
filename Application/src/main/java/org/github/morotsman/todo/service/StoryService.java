package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.unit_of_work.Story;

import java.util.List;
import java.util.Set;

public interface StoryService {

    public Story createStory(String teamName, Story story);

    public void deleteStory(String teamName, String storyName);

    public Story getStory(String teamName, String storyName);

    public Set<Story> findStories(String teamName);

}
