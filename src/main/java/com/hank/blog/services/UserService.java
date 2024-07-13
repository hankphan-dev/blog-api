package com.hank.blog.services;

import com.hank.blog.dtos.UserDto;
import com.hank.blog.entities.UserEntity;
import com.hank.blog.repositories.UserRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  /**
   * Get UserEntity by username and password
   *
   * @param username String
   * @param password String
   * @return UserEntity
   */
  @Transactional
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

  /**
   * Get all users
   * @return List UserDto
   */
  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream().map(
        userEntity -> modelMapper.map(userEntity, UserDto.class)
    ).toList();
  }
}
