package org.github.morotsman.todo.controller.converter;


import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.web.dto.StoryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StoryToStoryDTO implements Converter<Story,StoryDTO> {

    @Override
    public StoryDTO convert(Story story) {
        StoryDTO result = new StoryDTO();
        result.setDescription(story.getDescription());
        result.setEstimate(story.getEstimate());
        result.setName(story.getName());
        return result;
    }
}
