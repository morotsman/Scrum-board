package org.github.morotsman.todo.controller.converter;



import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.web.dto.StoriesDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class StoriesToStoriesDTO implements Converter<List<Story>, StoriesDTO> {

    private StoryToStoryDTO storyToStoryDTO;

    @Override
    public StoriesDTO convert(List<Story> stories) {
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
