package me.chahyunho.projectweek1.domain.user.service;


import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.exception.ServiceException;
import me.chahyunho.projectweek1.common.enums.exception.ServiceExceptionCode;
import me.chahyunho.projectweek1.domain.user.dto.UserRequest;
import me.chahyunho.projectweek1.domain.user.dto.UserResponse;
import me.chahyunho.projectweek1.domain.user.entity.User;
import me.chahyunho.projectweek1.domain.user.mapper.UserMapper;
import me.chahyunho.projectweek1.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserResponse create(UserRequest request) {

    // 이메일 검증
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }

    // 암호화
    request.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));

    User savedUser = userRepository.save(userMapper.toEntity(request));

    return userMapper.toUserResponse(savedUser);
  }

}
