package com.socialmedia.socialmediaapp.repository;

import com.socialmedia.socialmediaapp.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
