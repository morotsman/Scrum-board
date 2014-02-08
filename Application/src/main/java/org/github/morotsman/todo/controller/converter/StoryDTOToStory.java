package org.github.morotsman.todo.controller.converter;


import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.web.dto.StoryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StoryDTOToStory implements Converter<StoryDTO,Story> {

    @Override
    public Story convert(StoryDTO story) {
        Story result = new Story();
        result.setName(story.getName());
        result.setEstimate(story.getEstimate());
        result.setDescription(story.getDescription());
        return result;
    }
}
