package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@Tag(name = "Comment Controller", description = "APIs for managing comments on posts.")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Fetch all comments for a given post.
     *
     * @param postId The ID of the post
     * @return List of CommentDto
     */
    @Operation(summary = "Get all comments for a post", description = "Retrieve all comments for a specific post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved comments for the post"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(
            @Parameter(description = "ID of the post for which comments are fetched", required = true) @PathVariable long postId) {
        List<CommentDto> allCommentsByPostId = this.commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(allCommentsByPostId, HttpStatus.OK);
    }

    /**
     * Fetch a comment by its comment ID and post ID.
     *
     * @param postId     The ID of the post
     * @param commentId  The ID of the comment
     * @return CommentDto
     */
    @Operation(summary = "Get a comment by post ID and comment ID", description = "Retrieve a specific comment by post ID and comment ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the comment"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "ID of the comment", required = true) @PathVariable long commentId) {
        CommentDto commentDtoOfPostIdAndCommentId = this.commentService.getCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(commentDtoOfPostIdAndCommentId, HttpStatus.OK);
    }

    /**
     * Fetch all comments for a specific post by username.
     *
     * @param postId   The ID of the post
     * @param userName The username of the commenter
     * @return List of CommentDto
     */
    @Operation(summary = "Get all comments by username for a post", description = "Retrieve all comments from a specific user for a post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the comments by the user"),
            @ApiResponse(responseCode = "404", description = "Post or comments by user not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/posts/{postId}/comments/{userName}")
    public ResponseEntity<List<CommentDto>> getCommentByUserName(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "Username of the commenter", required = true) @PathVariable String userName) {
        List<CommentDto> commentDtoOfPostIdAndUserName = this.commentService.getCommentByPostIdAndUserName(postId, userName);
        return new ResponseEntity<>(commentDtoOfPostIdAndUserName, HttpStatus.OK);
    }

    /**
     * Create a new comment for a post.
     *
     * @param postId     The ID of the post
     * @param commentDto The comment data to be saved
     * @return Created CommentDto
     */
    @Operation(summary = "Create a new comment for a post", description = "Create a new comment on a specific post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "Comment data to be created", required = true) @RequestBody CommentDto commentDto) {
        CommentDto saveCommentEntity = this.commentService.createCommentForPost(postId, commentDto);
        return new ResponseEntity<>(saveCommentEntity, HttpStatus.CREATED);
    }

    /**
     * Update a comment for a specific post.
     *
     * @param postId     The ID of the post
     * @param commentId  The ID of the comment
     * @param commentDto The new comment data
     * @return Updated CommentDto
     */
    @Operation(summary = "Update an existing comment", description = "Update the details of a specific comment for a post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment successfully updated"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "ID of the comment", required = true) @PathVariable long commentId,
            @Parameter(description = "Updated comment data", required = true) @RequestBody CommentDto commentDto) {
        CommentDto updateCommentDto = this.commentService.updateCommentByPostIdAndCommentId(postId, commentId, commentDto);
        return new ResponseEntity<>(updateCommentDto, HttpStatus.OK);
    }

    /**
     * Delete a comment by post ID and comment ID.
     *
     * @param postId    The ID of the post
     * @param commentId The ID of the comment
     * @return Response message
     */
    @Operation(summary = "Delete a comment", description = "Delete a specific comment from a post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the comment"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "ID of the comment", required = true) @PathVariable long commentId) {
        String message = this.commentService.deleteCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Delete all comments for a specific post.
     *
     * @param postId The ID of the post
     * @return Response message
     */
    @Operation(summary = "Delete all comments for a post", description = "Delete all comments from a specific post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted all comments"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<String> deleteAllCommentsByPostId(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId) {
        String message = this.commentService.deleteAllCommentForThePostId(postId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
