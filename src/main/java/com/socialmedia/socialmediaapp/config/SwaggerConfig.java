package com.socialmedia.socialmediaapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Media API")
                        .version("v1")
                        .description("API documentation for the Social Media App")
                        .contact(new Contact()
                                .name("udhaya j")
                                .url("https://www.linkedin.com/in/udhaya-j-115363333/")
                                .email("udhayaj735@gmail.com"))
                );
    }
}