package org.example.prj_part_1.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.prj_part_1.common.exception.ServiceException;
import org.example.prj_part_1.common.exception.ServiceExceptionCode;
import org.example.prj_part_1.domain.user.dto.UserCreateRequest;
import org.example.prj_part_1.domain.user.dto.UserCreateResponse;
import org.example.prj_part_1.domain.user.entity.User;
import org.example.prj_part_1.domain.user.mapper.UserMapper;
import org.example.prj_part_1.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserCreateResponse create(UserCreateRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }

    String hashedPassword = passwordEncoder.encode(request.getPassword());
    User user = userRepository.save(userMapper.createRequestToEntity(request, hashedPassword));
    return userMapper.toCreateResponse(user);
  }

}
