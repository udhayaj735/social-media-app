package com.socialmedia.socialmediaapp.service.impl;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.entity.CommentEntity;
import com.socialmedia.socialmediaapp.entity.PostEntity;
import com.socialmedia.socialmediaapp.exceptions.ResourceNotFoundException;
import com.socialmedia.socialmediaapp.repository.CommentRepository;
import com.socialmedia.socialmediaapp.repository.PostRepository;
import com.socialmedia.socialmediaapp.service.CommentService;
import com.socialmedia.socialmediaapp.utils.CommentEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER= LoggerFactory.getLogger(CommentServiceImpl.class);
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
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {

        //Fetch post Entity using post Repositary from post id
        PostEntity postEntity= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Id Not Found :: "+postId));
        //Fetch comment Entity using comment Repositary from comment id
        CommentEntity commentEntity=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment Id Not Found::"+commentId));

        //validate comment belong to that particular post
        if(commentEntity!=null && postEntity!=null)
        {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId()))
            {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
        }
        //if valid then update old comment details with new comment Dto
        if(commentEntity!=null&& commentDto!=null){
            commentEntity.setEmail(commentDto.getEmail());
            commentEntity.setUserName(commentDto.getUserName());
            commentEntity.setBody(commentDto.getBody());
        }
        //save the new comment entity
        CommentEntity newlySavedCommentEntity=this.commentRepository.save(commentEntity);

        //Return newly updated comment

        return this.commentEntityMapper.mapCommentEntityToCommentDto(newlySavedCommentEntity);
    }

    @Override
    public String deleteCommentByPostIdAndCommentId(long postId, long commentId) {
        //Fetch post Entity using post Repositary from post id
        PostEntity postEntity= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Id Not Found :: "+postId));
        //Fetch comment Entity using comment Repositary from comment id
        CommentEntity commentEntity=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment Id Not Found::"+commentId));

        //validate comment belong to that particular post
        if(commentEntity!=null && postEntity!=null)
        {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId()))
            {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
        }
        this.commentRepository.delete(commentEntity);
        return "Successfully Deleted comment: "+  commentId;
    }

    @Override
    public String deleteAllCommentForThePostId(long postId) {
        this.commentRepository.deleteByPostId(postId);
        return "Successfully Deleted comments for the post Id: "+  postId;
    }

    @Override
    public List<CommentDto> getCommentByPostIdAndUserName(long postId,String userName) {
        //Validtae or fetch the username from comment table of DB
        List<CommentEntity> CommentsByUserName = this.commentRepository.findByUserName(userName);

        PostEntity postEntity= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Id Not Found :: "+postId));


        if(!CommentsByUserName.isEmpty()&&postEntity!=null)
        {
            return CommentsByUserName.stream()
                    .map(commentEntity -> this.commentEntityMapper.mapCommentEntityToCommentDto(commentEntity))
                    .toList();
        }
        throw new RuntimeException("Bad Request");
    }
}
