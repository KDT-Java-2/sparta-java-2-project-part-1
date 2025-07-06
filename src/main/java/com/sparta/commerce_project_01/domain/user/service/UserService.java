package com.sparta.commerce_project_01.domain.user.service;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.user.dto.UserCreateRequest;
import com.sparta.commerce_project_01.domain.user.dto.UserResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserSearchResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserUpdateStatusRequest;
import com.sparta.commerce_project_01.domain.user.entity.User;
import com.sparta.commerce_project_01.domain.user.mapper.UserMapper;
import com.sparta.commerce_project_01.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserResponse getUserById(Long id) {
    return userMapper.toResponse(getUser(id));
  }

  // Optional을 사용하는 다른 메서드들도 비슷하게 수정
  public UserResponse updateStatus(Long id, UserUpdateStatusRequest request) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
    // 상태 업데이트 로직
    return userMapper.toResponse(user);
  }

  public void delete(Long id) {

    User user = userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
    userRepository.delete(user);
  }

  public UserResponse update(Long id, UserCreateRequest request) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
    // 업데이트 로직
    return userMapper.toResponse(user);
  }

  private User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
  }

  public UserResponse save(@Valid UserCreateRequest userRequest) {
    User user = userMapper.toEntity(userRequest);
    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

  public List<UserSearchResponse> searchAll() {
    return null;
  }

  public UserResponse getById(Long userId) {
    return null;
  }
}