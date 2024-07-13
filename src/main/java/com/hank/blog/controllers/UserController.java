package com.hank.blog.controllers;

import com.hank.blog.common.ApiResponse;
import com.hank.blog.dtos.UserDto;
import com.hank.blog.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserDto>>> index() {
    List<UserDto> userDtos =  userService.getAllUsers();

    ApiResponse<List<UserDto>> response = ApiResponse.success(userDtos);

    return new ResponseEntity<>(response, response.code());
  }
}
