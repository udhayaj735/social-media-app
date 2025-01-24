package com.socialmedia.socialmediaapp.service;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.payload.PostResponse;

import java.util.List;


public interface PostService {

    PostResponse getAllPosts(int pageNo,int pageSize);

    /*1.GET all the posts*/
    List<PostDto> getAllPosts();

    /*2.GET Post by Id*/
    PostDto getPostById(long id);

    /*3.CREATE Post*/
    PostDto createPost(PostDto postDtoToBeCreated);

    /*4.Update Existing Post*/
    PostDto updatePost(PostDto postDto, long id);

    /*5.Delete Post*/
    boolean deletePostById(long postIdToBeDeleted);

}
