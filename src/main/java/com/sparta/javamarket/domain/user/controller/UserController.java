package com.sparta.javamarket.domain.user.controller;

import com.sparta.javamarket.common.response.ApiResponse;
import com.sparta.javamarket.domain.user.dto.UserCreateRequest;
import com.sparta.javamarket.domain.user.dto.UserSearchResponse;
import com.sparta.javamarket.domain.user.entity.User;
import com.sparta.javamarket.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/findAll")
  public ApiResponse<List<UserSearchResponse>> getAllUsers() {
    return ApiResponse.success(userService.getAllUser());
  }

  @GetMapping("/{userId}")
  public ApiResponse getUserId(@PathVariable Long userId) {
    return ApiResponse.success(userService.getUserById(userId));
  }

  @GetMapping("/mail")
  public ApiResponse findByMail(@RequestParam String email){
    return ApiResponse.success(userService.getUserByEmail(email));
  }


  @GetMapping("/name")
  public ApiResponse<List<UserSearchResponse>> findByName(@RequestParam String name){
    return ApiResponse.success(userService.getUserByName(name));
  }

  @PostMapping
  public void create(@Valid @RequestBody UserCreateRequest request) {
    System.out.println(request.toString());
  }

}
