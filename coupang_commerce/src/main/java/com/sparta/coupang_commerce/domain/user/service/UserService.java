package com.sparta.coupang_commerce.domain.user.service;

import com.sparta.coupang_commerce.common.utils.HashBuilder;
import com.sparta.coupang_commerce.domain.user.dto.UserCreateRequest;
import com.sparta.coupang_commerce.domain.user.dto.UserResponse;
import com.sparta.coupang_commerce.domain.user.entity.User;
import com.sparta.coupang_commerce.domain.user.mapper.UserMapper;
import com.sparta.coupang_commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserResponse createUser(UserCreateRequest request) {

    String passwordHash = HashBuilder.sha256(request.getPassword());
    String phoneNumHash = HashBuilder.sha256(request.getPhoneNum());

    User user = userMapper.toCreateEntity(request, passwordHash, phoneNumHash);

    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

}
