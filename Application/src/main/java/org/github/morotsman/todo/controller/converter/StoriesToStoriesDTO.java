package org.github.morotsman.todo.controller.converter;



import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.web.dto.StoriesDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Component
public class StoriesToStoriesDTO implements Converter<Collection<Story>, StoriesDTO> {

    private StoryToStoryDTO storyToStoryDTO;

    @Override
    public StoriesDTO convert(Collection<Story> stories) {
        StoriesDTO result = new StoriesDTO();
        for(Story each: stories){
            result.getStories().add(storyToStoryDTO.convert(each));
        }

        return result;
    }

    @Resource
    public void setStoryToStoryDTO(StoryToStoryDTO storyToStoryDTO) {
        this.storyToStoryDTO = storyToStoryDTO;
    }
}
