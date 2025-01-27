package com.socialmedia.socialmediaapp.utils;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

    public CommentDto mapCommentEntityToCommentDto(final CommentEntity commentEntity) {
        if (commentEntity != null) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(commentEntity.getId());
            commentDto.setUserName(commentEntity.getUserName());
            commentDto.setEmail(commentEntity.getEmail());
            commentDto.setBody(commentEntity.getBody());
            return commentDto;
        }
        return null;
    }

    /*map or covert postDto to post entity*/

    public CommentEntity mapCommentDtoToCommentEntity(final CommentDto commentDto) {
        if (commentDto != null) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setUserName(commentDto.getUserName());
            commentEntity.setEmail(commentDto.getEmail());
            commentEntity.setBody(commentDto.getBody());
            return commentEntity;
        }
        return null;
    }
}
