package com.socialmedia.socialmediaapp.service.impl;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.entity.PostEntity;
import com.socialmedia.socialmediaapp.exceptions.ResourceNotFoundException;
import com.socialmedia.socialmediaapp.payload.PostResponse;
import com.socialmedia.socialmediaapp.repositary.PostRepository;
import com.socialmedia.socialmediaapp.service.PostService;
import com.socialmedia.socialmediaapp.utils.PostEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger LOGGER= LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostEntityMapper postEntityMapper;


    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {
       Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<PostEntity> postEntityList=postRepository.findAll(pageable);
        if(postEntityList!=null)
        {
          List<PostDto> postDtoList=  postEntityList.stream()
                    .map(postEntity -> this.postEntityMapper.mapPostEntityToPostDto(postEntity))
                    .collect(Collectors.toList());
         PostResponse postReponse= PostResponse.builder()
                  .content(postDtoList)
                  .pageNo(postEntityList.getNumber())
                  .pageSize(postEntityList.getSize())
                  .totalElements(postEntityList.getTotalElements())
                  .totalPages(postEntityList.getTotalPages())
                  .isLastPage(postEntityList.isLast())
                  .build();
          return postReponse;
        }
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        if (postEntities != null) {
            List<PostDto> postDtos = postEntities.stream()
                    .map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
                    .collect(Collectors.toList());
            return postDtos;
        }
        return null;
    }

    @Override
    public PostDto getPostById(long id) {
        Optional<PostEntity> optionalEntity = this.postRepository.findById(id);
        return optionalEntity.map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
                .orElseThrow(() -> new RuntimeException("Post not found by id:" + id));
    }

    @Override
    public PostDto createPost(PostDto postDtoToBeCreated) {
        PostEntity entityToSave = this.postEntityMapper.mapPostDtoToPostEntity(postDtoToBeCreated);
        return this.postEntityMapper.mapPostEntityToPostDto(this.postRepository.save(entityToSave));
    }

    @Override
    public PostDto updatePost(PostDto postDto, long postDtoToBeUpdated) {
        PostEntity postEntityToBeUpdated = this.postRepository.findById(postDtoToBeUpdated).orElseThrow(() ->
                new RuntimeException("Post Id:" + postDtoToBeUpdated + " not found"));
        postEntityToBeUpdated.setTitle(postDto.getTitle());
        postEntityToBeUpdated.setDescription(postDto.getDescription());
        postEntityToBeUpdated.setContent(postDto.getContent());
        PostEntity postEntityUpdated = this.postRepository.save(postEntityToBeUpdated);
        return this.postEntityMapper.mapPostEntityToPostDto(postEntityUpdated);
    }

    @Override
    public boolean deletePostById(long postIdToBeDeleted) {
        try {
          PostEntity postEntity=  this.postRepository.findById(postIdToBeDeleted)
                    .orElseThrow(()-> new ResourceNotFoundException("Post not found by id:"+postIdToBeDeleted));
            this.postRepository.delete(postEntity);
        } catch (Exception e) {
           LOGGER.error("Exception while the Posts by Id:{}",postIdToBeDeleted);
           return false;
        }
        return true;
    }
}
