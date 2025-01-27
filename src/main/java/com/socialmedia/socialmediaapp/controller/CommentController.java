package com.socialmedia.socialmediaapp.controller;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //get all Commentsby post id
    ///post/{postId}/comments/
    /*@GetMapping("/posts/{postId}/comments")
    public List<ResponseEntity>getAllCommentsByPostId(@PathVariable long postId)
    {
        List<CommentDto> allCommentsByPostId = this.commentService.getAllCommentsByPostId(postId);
        return new List<ResponseEntity><>(allCommentsByPostId,HttpStatus.OK);
    }*/



    //Create Comment
    // /post/{postId}/comments

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto)
    {
        CommentDto saveCommentEntity=this.commentService.createCommentForPost(postId,commentDto);
        return new ResponseEntity<>(saveCommentEntity, HttpStatus.CREATED);
    }

}
