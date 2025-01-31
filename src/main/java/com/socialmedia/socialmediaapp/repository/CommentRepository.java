package com.socialmedia.socialmediaapp.repository;

import com.socialmedia.socialmediaapp.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    @Query(value="SELECT * FROM Comments where post_id=?1",nativeQuery = true)
    List<CommentEntity> findByPostId(long postId);
    @Query(value="SELECT * FROM Comments where user_name=?1",nativeQuery = true)
    List<CommentEntity> findByUserName(String userName);
    @Modifying
    @Transactional
    @Query(value="DELETE FROM Comments WHERE post_id=?1",nativeQuery = true)
    void deleteByPostId(long postId);
}
