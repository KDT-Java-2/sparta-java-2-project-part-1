package com.sparta.commerce.domain.user.service;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.user.entity.User;
import com.sparta.commerce.domain.user.entity.UserCreateRequest;
import com.sparta.commerce.domain.user.entity.UserCreateResponse;
import com.sparta.commerce.domain.user.mapper.UserCreateResponseMapper;
import com.sparta.commerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserCreateResponseMapper userMapper;

  @Transactional
  public UserCreateResponse createUser(UserCreateRequest createRequest) {
    // email 중복 체크
    if(userRepository.existsByEmail(createRequest.getEmail())) {
      throw new ServiceException(ServiceExceptionCode.EMAIL_ALREADY_EXISTS);
    }

    String encodedPassword = createRequest.getPassword();
    User newUser = User.builder()
        .email(createRequest.getEmail())
        .passwordHash(encodedPassword)
        .name(createRequest.getUsername())
        .phoneNumber(createRequest.getPhoneNumber())
        .build();

    User savedUser = userRepository.save(newUser);

    return userMapper.toResponse(savedUser);
  }
}
