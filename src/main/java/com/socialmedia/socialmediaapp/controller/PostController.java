package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    /*get all posts*/
    //v1/api/posts
    @GetMapping
    public List<PostDto> fetchAllPosts()
    {
      return  this.postService.getAllPosts();
    }

    /*get post by Id*/
    //v1/api/posts/{postId}
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

    /*Delete Post*/



}
