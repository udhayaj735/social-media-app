package com.socialmedia.socialmediaapp.service.impl;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.entity.PostEntity;
import com.socialmedia.socialmediaapp.repositary.PostRepository;
import com.socialmedia.socialmediaapp.service.PostService;
import com.socialmedia.socialmediaapp.utils.PostEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostEntityMapper postEntityMapper;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities=postRepository.findAll();
        if(postEntities!=null)
        {
           return postEntities.stream()
                   .map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
                   .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public PostDto getPostById(long id) {
       Optional<PostEntity> optionalEntity= this.postRepository.findById(id);
      return optionalEntity.map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
               .orElseThrow(()->new RuntimeException("Post not found by id:"+id));
    }

    @Override
    public PostDto createPost(PostDto postDtoToBeCreated) {
       PostEntity entityToSave= this.postEntityMapper.mapPostDtoToPostEntity(postDtoToBeCreated);
       return this.postEntityMapper.mapPostEntityToPostDto(this.postRepository.save(entityToSave));
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        return null;
    }

    @Override
    public boolean deletePostById(long postIdToBeDeleted) {
        return false;
    }
}
