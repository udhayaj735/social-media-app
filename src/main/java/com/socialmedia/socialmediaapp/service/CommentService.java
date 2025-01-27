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
    CommentDto updateCommentForPost(long postId,CommentDto commentDto, long commentId);

    /*5.Delete Comment for the post*/
    boolean deleteCommentByIdAndPostId(long postId,long commentIdToBeDeleted);
}
