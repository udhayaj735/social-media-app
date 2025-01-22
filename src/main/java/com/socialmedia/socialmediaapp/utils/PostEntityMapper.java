package com.socialmedia.socialmediaapp.utils;

import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.entity.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostEntityMapper {

    /*Map or convert post entity to post Dto*/
    public PostDto mapPostEntityToPostDto(final PostEntity postEntity)
    {
        PostDto postDto=new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        return postDto;
    }

    /*map or covert postDto to post entity*/

    public PostEntity mapPostDtoToPostEntity(final PostDto postDto)
    {
        PostEntity postEntity=new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());
        return postEntity;
    }
}
