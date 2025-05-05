package com.socialmedia.socialmediaapp.repository;

import com.socialmedia.socialmediaapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    //findByEmail
    Optional<UserEntity> findByEmail(String email);

    //findByUserName
    Optional<UserEntity> findByUserName(String username);
    //findByUserName or email
    Optional<UserEntity> findByUsernameOrEmail(String username,String email);

}
