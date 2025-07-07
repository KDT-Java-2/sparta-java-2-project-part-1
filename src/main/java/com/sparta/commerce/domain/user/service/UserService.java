package com.sparta.commerce.domain.user.service;

import static com.sparta.commerce.common.exception.ServiceExceptionCode.*;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.user.dto.UserRequest;
import com.sparta.commerce.domain.user.dto.UserResponse;
import com.sparta.commerce.domain.user.entity.User;
import com.sparta.commerce.domain.user.mapper.UserMapper;
import com.sparta.commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserResponse create(UserRequest request){
    validateDuplicate(request);

    User user = User.builder()
        .email(request.getEmail())
        .nickname(request.getNickname())
        .birthDate(request.getBirthDate())
        .encryptPassword(request.getPassword())
        .gender(request.getGender())
        .isVerified(false)
        .marketingAgree(request.getMarketingAgree())
        .build();
    userRepository.save(user);
    return userMapper.toResponse(user);
  }

  private void validateDuplicate(UserRequest request) {
    userRepository.findByEmail(request.getEmail())
        .ifPresent(user -> {
          throw new ServiceException(DUPLICATE_EMAIL);
        });
  }
}
