package com.socialmedia.socialmediaapp.repositary;

import com.socialmedia.socialmediaapp.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
