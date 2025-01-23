package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

     @Autowired
    private PostService postService;
    /*get all posts*/
    @GetMapping
    public List<PostDto> fetchAllPosts()
    {
      return  this.postService.getAllPosts();
    }

    /*get post by Id*/
    @GetMapping("/{postId}")
    public PostDto fetchPostById(@PathVariable long postId)
    {
        return this.postService.getPostById(postId);
    }

    /*create post*/
    @PostMapping
    public PostDto savePost(@RequestBody PostDto postDto)
    {
        return this.postService.createPost(postDto);
    }

    /*update post*/

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto postDtoToBeUpdated,@PathVariable long postId)
    {
        return this.postService.updatePost(postDtoToBeUpdated,postId);
    }

    /*Delete Post*/
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
        Boolean isDeleted = this.postService.deletePostById(postId);
        if (isDeleted) {
            return ResponseEntity.ok("Post: " + postId + " deleted Successfully");
        } else {
            return new ResponseEntity<>("Error Post " + postId + " Error while deleting Post", HttpStatus.NOT_FOUND);
        }
    }
    }
