package com.socialmedia.socialmediaapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfiguartion {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
