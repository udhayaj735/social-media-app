package com.socialmedia.socialmediaapp.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String userName;
    private String email;
    private String body;
}
