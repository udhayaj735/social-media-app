package com.socialmedia.socialmediaapp.service;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.payload.PostResponse;

import java.util.List;

public interface CommentService {
    CommentService getAllComments(int pageNo, int pageSize);

    CommentService getAllComments(int pageNo,int pageSize,String sortBy,String sortDirection);
    /*1.GET all the comments by post Id*/
    List<CommentDto> getAllCommentsByPostId(long postId);

    /*2.GET Comment by PostId and Comment Id*/
    CommentDto getCommentByPostIdAndCommentId(long postId,long coomentId);

    /*3.CREATE Comment*/
    CommentDto createCommentForPost(long postId,CommentDto CommentToBeCreated);

    /*4.Update Existing Comment for the post*/
    CommentDto updateCommentByPostIdAndCommentId(long postId,long commentId,CommentDto commentDto);

    /*5.Delete Particular Comment for the post Id and comment Id */
    String deleteCommentByPostIdAndCommentId(long postId,long commentId);

      /*6.Delete all Comments  for the post Id */
    String deleteAllCommentForThePostId(long postId);

    /*7. Get Comment By UserName*/
    List<CommentDto> getCommentByPostIdAndUserName(long postId,String userName);

}
