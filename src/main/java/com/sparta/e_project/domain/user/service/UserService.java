package com.sparta.e_project.domain.user.service;

import com.sparta.e_project.common.exception.ServiceException;
import com.sparta.e_project.common.exception.ServiceExceptionCode;
import com.sparta.e_project.common.utils.PasswordUtil;
import com.sparta.e_project.domain.user.dto.UserRequest;
import com.sparta.e_project.domain.user.entity.User;
import com.sparta.e_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordUtil passwordUtil;

  @Transactional
  public User createUser(UserRequest userRequest) {
    // 이메일 중복 체크
    if (userRepository.existsByEmail(userRequest.getEmail())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }
    return userRepository.save(User.builder()
        .email(userRequest.getEmail())
        .name(userRequest.getName())
        .passwordHash(passwordUtil.encode(userRequest.getPasswordHash()))
        .build());
  }
}
