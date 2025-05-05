package com.socialmedia.socialmediaapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty
    @NotNull
    @Size(min=3,message = "User Name should have at least 3 characters")
    private String userName;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min=7,message = "Comment body should have at least 7 Characters")
    private String body;
}
