package com.socialmedia.socialmediaapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info=@Info(
                title="Social Media API",
                version="v1.0",
                description="This API documentation allows to create,fetch,update and delete post and comments on a Social Media App",
                license=@License(name="MIT",url="https://opensourse.org/license/MIT"),
                contact=@Contact (name ="Udhaya",email = "udhayaj735@gmail.com",url = "http://udhayaprtfolio.com")
                )
        )
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi postsApi() {
        return GroupedOpenApi.builder()
                .group("Posts Api")
                .packagesToScan("com.socialmedia.socialmediaapp.controller")
                .build();
    }
    @Bean
    public GroupedOpenApi CommentsApi() {
        return GroupedOpenApi.builder()
                .group("Comments Api")
                .packagesToScan("com.socialmedia.socialmediaapp.controller")
                .build();
    }

}