package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class StoriesDTO extends ResourceSupport{

    private List<StoryDTO> stories = new ArrayList<StoryDTO>();

    public List<StoryDTO> getStories() {
        return stories;
    }

    public void setStories(List<StoryDTO> stories) {
        this.stories = stories;
    }
}
