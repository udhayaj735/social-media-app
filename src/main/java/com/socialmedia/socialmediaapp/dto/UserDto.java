package com.socialmedia.socialmediaapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    @Size(min=3,message = "User Name should have at least 3 characters")
    private String userName;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Size(min=8,max=25,message = "User Name should have at least 8 characters")
   /* @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must meet complexity requirements")*/
    private String password;
}
