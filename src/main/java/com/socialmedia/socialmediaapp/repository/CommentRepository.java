package com.socialmedia.socialmediaapp.repository;

import com.socialmedia.socialmediaapp.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    @Query(value="SELECT * FROM Comments where post_id=?1",nativeQuery = true)
    List<CommentEntity> findByPostId(long postId);
}
