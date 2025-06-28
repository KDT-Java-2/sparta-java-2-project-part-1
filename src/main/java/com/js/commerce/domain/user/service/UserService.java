package com.js.commerce.domain.user.service;

import com.js.commerce.common.exception.ServiceException;
import com.js.commerce.common.exception.ServiceExceptionCode;
import com.js.commerce.domain.user.dto.UserCreateRequest;
import com.js.commerce.domain.user.dto.UserResponse;
import com.js.commerce.domain.user.entity.User;
import com.js.commerce.domain.user.mapper.UserMapper;
import com.js.commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Transactional
  public UserResponse register(UserCreateRequest request) {
    // 이메일 중복 검사
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }

    User user = userMapper.toEntity(request);
    User saved = userRepository.save(user);

    return userMapper.toResponse(saved);
  }
}
