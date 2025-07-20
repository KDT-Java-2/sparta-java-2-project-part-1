package com.sparta.ecommerce.domain.user.service;


import com.sparta.ecommerce.common.exception.ServiceException;
import com.sparta.ecommerce.common.exception.ServiceExceptionCode;
import com.sparta.ecommerce.domain.user.dto.UserCreateRequest;
import com.sparta.ecommerce.domain.user.dto.UserCreateResponse;
import com.sparta.ecommerce.domain.user.entity.User;
import com.sparta.ecommerce.domain.user.mapper.UserMapper;
import com.sparta.ecommerce.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  @Transactional
  public UserCreateResponse create(UserCreateRequest request) {
    List<User> userList = userRepository.findByEmail(request.getEmail());
    if (userList != null && !userList.isEmpty()) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_USER_EMAIL);
    }

    User param = userMapper.toEntity(request);

    User result = userRepository.save(param);

    return userMapper.toCreateResponse(result);
  }
}
