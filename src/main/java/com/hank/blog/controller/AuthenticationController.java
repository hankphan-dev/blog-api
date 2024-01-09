package com.hank.blog.controller;

import com.hank.blog.dto.UserDto;
import com.hank.blog.entity.UserEntity;
import com.hank.blog.payload.request.LoginRequest;
import com.hank.blog.payload.response.LoginResponse;
import com.hank.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@AllArgsConstructor
public class AuthenticationController {
  private final UserService userService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    UserDto userDto = userService.getUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    LoginResponse loginResponse = new LoginResponse();
    if (userDto != null) {
      loginResponse.setToken(userDto.getUsername() + userDto.getPassword());
    }

    return loginResponse;
  }
}
