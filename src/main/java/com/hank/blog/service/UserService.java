package com.hank.blog.service;

import com.hank.blog.dto.UserDto;
import com.hank.blog.entity.UserEntity;
import com.hank.blog.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;

  /**
   * Get UserEntity by username and password
   *
   * @param username String
   * @param password String
   * @return UserEntity
   */
  public @Nullable UserDto getUserByUsernameAndPassword(String username, String password) {
    UserEntity userEntity = userRepository.findFirstByUsernameAndPassword(username, password);
    if (userEntity != null) {
      UserDto userDto = new UserDto();
      userDto.setUsername(userEntity.getUsername());
      userDto.setPassword(userEntity.getPassword());
      return userDto;
    }
    return null;
  }
}
