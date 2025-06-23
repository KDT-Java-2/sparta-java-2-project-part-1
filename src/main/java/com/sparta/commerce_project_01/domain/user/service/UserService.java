package com.sparta.commerce_project_01.domain.user.service;

import com.sparta.commerce_project_01.domain.user.dto.UserRequest;
import com.sparta.commerce_project_01.domain.user.dto.UserResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserUpdateStatusRequest;
import com.sparta.commerce_project_01.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserResponse save(UserRequest userRequest) {
    return null;
  }

  public UserResponse getById(Long userId) {
    return null;
  }

  public UserResponse delete(Long userId) {
    return null;
  }

  public UserResponse update(Long userId, UserRequest userRequest) {
    return null;
  }

  public UserResponse updateStatus(Long userId, UserUpdateStatusRequest userUpdateStatusRequest) {
    return null;
  }


  public List<UserResponse> findAll() {
    return new ArrayList<>();
  }
}