package com.hank.blog.controllers;

import com.hank.blog.dtos.UserDto;
import com.hank.blog.payloads.requests.LoginRequest;
import com.hank.blog.payloads.responses.LoginResponse;
import com.hank.blog.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
