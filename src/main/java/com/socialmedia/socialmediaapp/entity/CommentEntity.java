package com.socialmedia.socialmediaapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    @Size(min=3,message = "User Name should have at least 3 characters")
    @Column(name="user_name")
    private String userName;

    @NotEmpty
    @NotNull
    @Email
    @Column(name="email")
    private String email;

    @NotNull
    @NotEmpty
    @Size(min=7,message = "Comment body should have at least 7 Characters")
    @Column(name="body")
    private String body;
    //ManyToOne Relationship
    //Multiple Comments on single post
    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private PostEntity postEntity;

    /*Future Enhancement
       Comments Table
Column Name	Data Type	Description
comment_id	INT (PK)	Unique identifier for the comment.
post_id	INT (FK)	The ID of the post to which the comment belongs.
user_id	INT (FK)	The ID of the user who made the comment.
content	TEXT	The content of the comment.
created_at	TIMESTAMP	Date and time when the comment was created.
parent_comment_id	INT (FK, NULL)	If the comment is a reply to another comment, this is the ID of the parent comment.
     */
}
