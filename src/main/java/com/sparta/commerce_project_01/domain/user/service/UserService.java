package com.sparta.commerce_project_01.domain.user.service;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.user.dto.UserCreateRequest;
import com.sparta.commerce_project_01.domain.user.dto.UserResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserSearchResponse;
import com.sparta.commerce_project_01.domain.user.dto.UserUpdateRequest;
import com.sparta.commerce_project_01.domain.user.entity.User;
import com.sparta.commerce_project_01.domain.user.mapper.UserMapper;
import com.sparta.commerce_project_01.domain.user.repository.UserQueryRepository;
import com.sparta.commerce_project_01.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserQueryRepository userQueryRepository;
  private final UserMapper userMapper;

  @Transactional(readOnly = true)
  public UserResponse getUserById(Long id) {
    return userMapper.toResponse(getUser(id));
  }

  // Optional을 사용하는 다른 메서드들도 비슷하게 수정
  public UserResponse updateStatus(Long id, UserUpdateRequest request) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
    // 상태 업데이트 로직
    return userMapper.toResponse(user);
  }

  public void delete(Long id) {
    User user = getUser(id);
    userRepository.delete(user);
  }

  public void update(Long id, UserUpdateRequest request) {
    User user = getUser(id);

    user.setName(request.getName());
    user.setEmail(request.getEmail());

    userRepository.save(user);

  }

  private User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
  }

  public void create(@Valid UserCreateRequest request) {

    userRepository.save(User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .passwordHash(request.getPassword()) // TODO: 패스워드 암호화 필요
        .cellPhone(request.getCellPhone())
        .isAcceptTerms(request.isAcceptTerms())
        .isAcceptPrivacy(request.isAcceptPrivacy())
        .isAcceptMarketing(request.isAcceptMarketing())
        .build());

//    userRepository.save(userMapper.toEntity(request));
  }

  @Transactional
  public Page<UserSearchResponse> searchAllUser() {
    return null;
  }
}