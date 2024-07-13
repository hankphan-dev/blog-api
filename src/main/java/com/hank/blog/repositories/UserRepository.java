package com.hank.blog.repositories;

import com.hank.blog.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  UserEntity findFirstByUsernameAndPassword(String username, String password);
}
