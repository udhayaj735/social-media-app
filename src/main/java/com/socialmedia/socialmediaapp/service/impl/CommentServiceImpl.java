package com.socialmedia.socialmediaapp.service.impl;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.entity.CommentEntity;
import com.socialmedia.socialmediaapp.entity.PostEntity;
import com.socialmedia.socialmediaapp.exceptions.ResourceNotFoundException;
import com.socialmedia.socialmediaapp.repository.CommentRepository;
import com.socialmedia.socialmediaapp.repository.PostRepository;
import com.socialmedia.socialmediaapp.service.CommentService;
import com.socialmedia.socialmediaapp.utils.CommentEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentEntityMapper commentEntityMapper;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentService getAllComments(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public CommentService getAllComments(int pageNo, int pageSize, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {

        List<CommentEntity> commentEntities =  commentRepository.findByPostId(postId);
        if (commentEntities != null && !commentEntities.isEmpty() ) {
          return commentEntities.stream()
                    .map(commentEntity -> this.commentEntityMapper.mapCommentEntityToCommentDto(commentEntity))
                  .toList();
        }
        return null;
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {
        //Validtae or fetch the post entity from post table of DB

        PostEntity postEntity= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Id Not Found :: "+postId));

        //fetch the comment by comment id
        CommentEntity commentEntity=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment Id Not Found::"+commentId));
        //validate the particular comment belongs to that post

        if(commentEntity!=null && postEntity!=null)
        {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId()))
            {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
            else {
                return this.commentEntityMapper.mapCommentEntityToCommentDto(commentEntity);
            }
        }
      throw new RuntimeException("Bad Request");
    }

    @Override
    public CommentDto createCommentForPost(long postId, CommentDto CommentToBeCreated) {
        //convert comment DTO to Comment Entity
        CommentEntity commentEntity = this.commentEntityMapper.mapCommentDtoToCommentEntity(CommentToBeCreated);
        //Fetch Post Entity From post table from DB
        PostEntity postEntity= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Id Not Found :: "+postId));
        //Attach or set comment to particular or associated post entity
        commentEntity.setPostEntity(postEntity);
        //save comment entity
        CommentEntity newlySavedCommentEntity=this.commentRepository.save(commentEntity);

        //map comment Entity to Comment Dto
        return this.commentEntityMapper.mapCommentEntityToCommentDto(newlySavedCommentEntity);
    }

    @Override
    public CommentDto updateCommentForPost(long postId, CommentDto commentDto, long commentId) {
        return null;
    }

    @Override
    public boolean deleteCommentByIdAndPostId(long postId, long commentIdToBeDeleted) {
        return false;
    }
}
