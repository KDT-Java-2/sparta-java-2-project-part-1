package com.sparta.javamarket.domain.user.controller;

import com.sparta.javamarket.domain.user.dto.UserCreateRequest;
import com.sparta.javamarket.domain.user.entity.User;
import com.sparta.javamarket.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public void create(@Valid @RequestBody UserCreateRequest request) {
    System.out.println(request.toString());
  }

}
