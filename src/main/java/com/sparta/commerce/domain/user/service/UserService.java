package com.sparta.commerce.domain.user.service;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.commerce.domain.user.dto.UserResponse;
import com.sparta.commerce.domain.user.entity.User;
import com.sparta.commerce.domain.user.mapper.UserMapper;
import com.sparta.commerce.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserResponse createUser(UserCreateRequest request) {
    Optional<User> existingEmail = userRepository.findByEmail(request.getEmail());
    if (existingEmail.isPresent()) {
      throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_EMAIL);
    }

    String encodePassword = passwordEncoder.encode(request.getPassword());
    User user = userMapper.toEntity(request, encodePassword);
    User savedUser = userRepository.save(user);

    return userMapper.toResponse(savedUser);
  }


}
