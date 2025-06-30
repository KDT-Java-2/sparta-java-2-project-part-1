package com.sparta.javamarket.domain.user.service;

import com.sparta.javamarket.common.exception.ServiceException;
import com.sparta.javamarket.common.exception.ServiceExceptionCode;
import com.sparta.javamarket.domain.user.dto.UserSearchResponse;
import com.sparta.javamarket.domain.user.entity.User;
import com.sparta.javamarket.domain.user.mapper.UserMapper;
import com.sparta.javamarket.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Transactional
  public List<UserSearchResponse> getAllUser() {
    return userRepository.findAll().stream()
        .map(userMapper::toSearch)
        .toList();
  }

  @Transactional
  public UserSearchResponse getUserById(Long userId) {
    return userMapper.toResponse(getUser(userId));
  }

  @Transactional
  public UserSearchResponse getUserByEmail(String email){
    return userRepository.findFirstByEmail(email);
  }

  @Transactional
  public List<UserSearchResponse> getUserByName(String name){
    return userRepository.findByName(name);
  }

  private User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
  }

}
