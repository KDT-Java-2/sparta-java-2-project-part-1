package com.sparta.project1.domain.user.service;

import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.user.dto.UserRequest;
import com.sparta.project1.domain.user.dto.UserResponse;
import com.sparta.project1.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  //회원가입
  @Transactional
  public ApiResponse<UserResponse> create(@Valid @RequestBody UserRequest request) {
    //서비스단 로직완성되면 넣을것
    return ApiResponse.success();
  }
}
