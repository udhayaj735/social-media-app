package com.socialmedia.socialmediaapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*for unique id*/
    private Long id;

    @NotEmpty
    @Size(min=3,message = "Post Title should have at least 3 Characters")
    @Column(name="title")
    private String title;

    @NotEmpty
    @Size(min=5,message = "Post Description should have at least 5 Characters")
    @Column(name="description")
    private String description;

    @NotEmpty
    @Size(min=7,message = "Post Content should have at least 7 Characters")
    @Column(name="content")
    private String content;



    //oneToMany Relationship
    //single post can have multiple comments.
    @OneToMany(mappedBy = "postEntity")
     private Set<CommentEntity> comments=new HashSet<>();

    /*Future Enhancement
    Posts Table
Column Name	Data Type	Description
post_id	INT (PK)	Unique identifier for the post.
user_id	INT (FK)	The ID of the user who created the post.
content	TEXT	The content of the post (could include text, images, or videos).
post_type	ENUM	The type of the post (e.g., text, image, video, link).
created_at	TIMESTAMP	Date and time when the post was created.
updated_at	TIMESTAMP	Date and time when the post was last updated.
visibility	ENUM	Privacy level (e.g., public, friends, private).
status	ENUM	Status of the post (e.g., active, deleted, flagged).
location	VARCHAR(255)	Location associated with the post (if applicable).
media_url	VARCHAR(255)	URL of media (image/video), if the post includes media.
tags	VARCHAR(255)	A list of tags associated with the post (comma-separated).
    */
}
