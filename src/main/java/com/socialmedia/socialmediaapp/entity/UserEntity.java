package com.socialmedia.socialmediaapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @NotNull
    @Column(name="name",nullable = false)
    private String name;

    @NotEmpty
    @NotNull
    @Size(min=3,message = "User Name should have at least 3 characters")
    @Column(name="username", nullable = false,unique = true)
    private String userName;

    @NotEmpty
    @NotNull
    @Email
    @Column(name="email",nullable = false,unique = true)
    private String email;

    @NotEmpty
    @NotNull
    @Column(name="password",nullable = false)
    @Size(min=8,max=25,message = "User Name should have at least 8 characters")
   /* @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must meet complexity requirements")*/
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<RoleEntity> roles;
}
