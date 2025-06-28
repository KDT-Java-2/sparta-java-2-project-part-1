package com.sparta.part_1.domain.user.service;

import com.sparta.part_1.common.exception.UserErrorCode;
import com.sparta.part_1.common.exception.UserServiceException;
import com.sparta.part_1.domain.user.dto.request.UserJoinRequest;
import com.sparta.part_1.domain.user.dto.response.UserJoinResponse;
import com.sparta.part_1.domain.user.entity.User;
import com.sparta.part_1.domain.user.mapper.UserEntityMapper;
import com.sparta.part_1.domain.user.repository.UserQueryRepository;
import com.sparta.part_1.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

  private final UserRepository userRepository;
  private final UserQueryRepository userQueryRepository;
  private final UserEntityMapper mapper;


  @Transactional
  public UserJoinResponse join(UserJoinRequest dto) {

    if (Boolean.TRUE.equals(hasSameEmail(dto.getEmail()))) {
      throw new UserServiceException(UserErrorCode.HAS_SAME_USER_EMAIL);
    }

    User save = userRepository.save(mapper.toUserEntity(dto));

    return mapper.toUserJoinResponse(save);
  }

  private boolean hasSameEmail(String email) {
    return userQueryRepository.hasSameUserEmail(email);
  }
}
