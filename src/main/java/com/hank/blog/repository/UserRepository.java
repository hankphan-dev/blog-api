package com.hank.blog.repository;

import com.hank.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  UserEntity findFirstByUsernameAndPassword(String username, String password);
}
