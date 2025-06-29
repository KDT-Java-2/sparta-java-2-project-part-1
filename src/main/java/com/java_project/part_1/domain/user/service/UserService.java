package com.java_project.part_1.domain.user.service;

import com.java_project.part_1.common.enums.Role;
import com.java_project.part_1.common.exception.ServiceException;
import com.java_project.part_1.common.exception.ServiceExceptionCode;
import com.java_project.part_1.domain.user.dto.UserCreateRequest;
import com.java_project.part_1.domain.user.entity.User;
import com.java_project.part_1.domain.user.mapper.UserMapper;
import com.java_project.part_1.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  //private final PasswordEncoder passwordEncoder;

  public User findUserByEmail(String email) {
    if (ObjectUtils.isEmpty(email)) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA);
    }

    return User.builder().build();
  }

  public void save(UserCreateRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ServiceException(ServiceExceptionCode.EMAIL_ALREADY_EXIST);
    }

    User user = User.builder()
        .userId(request.getEmail())
        .password(request.getPassword())
        .name(request.getName())
        .email(request.getEmail())
        .role(Role.User)
        .build();
    userRepository.save(user);
  }

}
