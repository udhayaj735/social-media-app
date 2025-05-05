package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.payload.PostResponse;
import com.socialmedia.socialmediaapp.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/api/posts")
@Tag(name = "Post Controller V2", description = "APIs for managing posts with pagination and sorting.")
public class PostControllerV2 {

    @Autowired
    private PostService postService;

    /**
     * Fetch all posts with pagination and sorting options.
     *
     * @param pageNo The page number to retrieve
     * @param pageSize The number of posts per page
     * @param sortBy The field by which to sort (e.g., "id")
     * @param sortDirection The direction of sorting, either "ASC" or "DESC"
     * @return A response object containing the paginated posts.
     */
    @Operation(summary = "Fetch all posts with pagination and sorting", description = "Retrieve all posts with pagination and sorting options.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the posts"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public PostResponse fetchAllPosts(
            @Parameter(description = "The page number to retrieve (0-based)", required = false)
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,

            @Parameter(description = "The number of posts per page", required = false)
            @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,

            @Parameter(description = "The field by which to sort (e.g., id)", required = false)
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,

            @Parameter(description = "The direction of sorting (ASC or DESC)", required = false)
            @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false) String sortDirection) {

        return this.postService.getAllPosts(pageNo, pageSize, sortBy, sortDirection);
    }
}
