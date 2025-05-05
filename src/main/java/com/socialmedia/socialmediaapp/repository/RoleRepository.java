package com.socialmedia.socialmediaapp.repository;

import com.socialmedia.socialmediaapp.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
Optional<RoleEntity> findByName(String name);
}
