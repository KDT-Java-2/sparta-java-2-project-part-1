package com.sparta.project1.domain.user.service;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.exception.ServiceExceptionCode;
import com.sparta.project1.common.response.ApiResponse;
import com.sparta.project1.domain.user.dto.UserRequest;
import com.sparta.project1.domain.user.dto.UserResponse;
import com.sparta.project1.domain.user.entity.User;
import com.sparta.project1.domain.user.mapper.UserMapper;
import com.sparta.project1.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  //회원가입
  @Transactional
  public UserResponse create(UserRequest request) {

    //이메일 중복체크
    if(userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }

    //password 암호화
    String encodePassword = passwordEncoder.encode(request.getPassword());

    User savedUser = userRepository.save(userRepository.save(User.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(encodePassword)
            .build()));

    return userMapper.toResponse(savedUser);
  }
}