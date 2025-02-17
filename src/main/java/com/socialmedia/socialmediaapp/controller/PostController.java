package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/api/posts")
@Tag(name="Post API",description = "API for managing posts,Allows users to fetch,create,update and delete posts.")
public class PostController {

    @Autowired
    private PostService postService;

    private static final Logger logger = Logger.getLogger(PostController.class.getName());

    /**
     * Fetch all posts.
     *
     * @return List of PostDto
     */
    @Operation(summary = "Fetch all posts", description = "Retrieve all posts from the social media platform.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the posts"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PostDto>> fetchAllPosts() {
        logger.info("Fetching all posts");
        List<PostDto> posts = this.postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Fetch a post by its ID.
     *
     * @param postId The ID of the post
     * @return PostDto
     */
    @Operation(summary = "Fetch a post by ID", description = "Retrieve a post using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the post"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> fetchPostById(
            @Parameter(description = "ID of the post to be fetched", required = true)
            @PathVariable long postId) {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * Create a new post.
     *
     * @param postDto Post data transfer object to be saved
     * @return Created PostDto
     */
    @Operation(summary = "Create a new post", description = "Create a new post in the social media platform.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<PostDto> savePost(
            @Parameter(description = "Post data to be created", required = true)
            @Valid
            @RequestBody PostDto postDto) {
        PostDto createdPost = this.postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    /**
     * Update an existing post.
     *
     * @param postDtoToBeUpdated The new post data
     * @param postId             The ID of the post to be updated
     * @return Updated PostDto
     */
    @Operation(summary = "Update a post", description = "Update the details of an existing post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the post"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @Parameter(description = "Updated post data", required = true)
            @Valid
            @RequestBody PostDto postDtoToBeUpdated,
            @Parameter(description = "ID of the post to be updated", required = true) @PathVariable long postId) {
        PostDto updatedPost = this.postService.updatePost(postDtoToBeUpdated, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    /**
     * Delete a post by its ID.
     *
     * @param postId ID of the post to be deleted
     * @return Response message indicating success or failure
     */
    @Operation(summary = "Delete a post by ID", description = "Delete a specific post using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @Parameter(description = "ID of the post to be deleted", required = true) @PathVariable long postId) {
        Boolean isDeleted = this.postService.deletePostById(postId);
        if (isDeleted) {
            return ResponseEntity.ok("Post " + postId + " deleted successfully");
        } else {
            return new ResponseEntity<>("Error while deleting Post " + postId, HttpStatus.NOT_FOUND);
        }
    }
}
