package org.github.morotsman.todo.config;


import org.github.morotsman.todo.controller.converter.StoriesToStoriesDTO;
import org.github.morotsman.todo.controller.converter.StoryDTOToStory;
import org.github.morotsman.todo.controller.converter.StoryToStoryDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

import javax.annotation.Resource;

@Configuration
public class ConverterConfig {

    @Resource
    private StoryToStoryDTO storyToStoryDTO;

    @Resource
    private StoryDTOToStory storyDTOToStory;

    @Resource
    private StoriesToStoriesDTO storiesToStoriesDTO;


    @Bean(name = "conversionService")
    public DefaultConversionService createConversionService(){
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(storyToStoryDTO);
        defaultConversionService.addConverter(storyDTOToStory);
        defaultConversionService.addConverter(storiesToStoriesDTO);
        return defaultConversionService;
    }

}
