package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.payload.PostResponse;
import com.socialmedia.socialmediaapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/api/posts")
public class PostControllerV2 {
    @Autowired
    private PostService postService;

    @GetMapping
    public PostResponse fetchallPosts(@RequestParam(value="pageNo",defaultValue="0",required = false) int pageNo,
                                      @RequestParam(value = "pageSize",defaultValue = "0",required = false) int pageSize)
    {
        return this.postService.getAllPosts(pageNo,pageSize);
    }

}
