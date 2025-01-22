package com.socialmedia.socialmediaapp.dto;
import lombok.*;
/*now this act as a proxy between all units*/
@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}
