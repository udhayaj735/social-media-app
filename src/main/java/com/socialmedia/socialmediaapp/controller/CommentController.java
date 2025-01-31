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
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable long postId)
    {
        List<CommentDto> allCommentsByPostId = this.commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(allCommentsByPostId,HttpStatus.OK);
    }

    //get comment by comment id and post id
    ///post/{postId}/comments/{commentId}
    /// p
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostIdAndCommentId(@PathVariable long postId,@PathVariable long commentId)
    {
        CommentDto commentDtoOfPostIdAndCommentId= this.commentService.getCommentByPostIdAndCommentId(postId,commentId);
        return new ResponseEntity<>(commentDtoOfPostIdAndCommentId,HttpStatus.OK);
    }

    //get all comment by username

    @GetMapping("/posts/{postId}/comments/'{userName}'")
    public ResponseEntity<List<CommentDto>> getCommentByUserName(@PathVariable long postId,@PathVariable String userName)
    {
      List<CommentDto> commentDtoOfPostIdAndUserName= this.commentService.getCommentByPostIdAndUserName(postId,userName);
        return new ResponseEntity<>(commentDtoOfPostIdAndUserName,HttpStatus.OK);
    }


    //Create Comment
    // /post/{postId}/comments

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto)
    {
        CommentDto saveCommentEntity=this.commentService.createCommentForPost(postId,commentDto);
        return new ResponseEntity<>(saveCommentEntity, HttpStatus.CREATED);
    }

    //Update Comment
    //post/{postId}/comments/{commentId}
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,
                                                    @PathVariable long commentId,
                                                    @RequestBody CommentDto commentDto)
    {
        CommentDto updateCommentDto =this.commentService.updateCommentByPostIdAndCommentId(postId,commentId,commentDto);
        return new ResponseEntity<>(updateCommentDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByPostIdAndCommentId(@PathVariable long postId,
                                                                        @PathVariable long commentId)
    {
        String message=this.commentService.deleteCommentByPostIdAndCommentId(postId,commentId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<String> deleteAllCommentsByPostId(@PathVariable long postId)
    {
        String messgae= this.commentService.deleteAllCommentForThePostId(postId);
        return new ResponseEntity<>(messgae,HttpStatus.OK);
    }

}
