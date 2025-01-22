package com.socialmedia.socialmediaapp.service;

import com.socialmedia.socialmediaapp.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

  /*1.GET all the posts*/
    List<PostDto> getAllPosts();

/*2.GET Post by Id*/
    PostDto getPostById(long id);
/*3.CREATE Post*/
    PostDto createPost(PostDto postDtoToBeCreated);
/*4.Update Existing Post*/
    PostDto updatePost(PostDto postDto,long id);
/*5.Delete Post*/
    boolean deletePostById(long postIdToBeDeleted);


}
