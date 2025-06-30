package com.sparta.javamarket.domain.user.service;

import com.sparta.javamarket.common.exception.ServiceException;
import com.sparta.javamarket.common.exception.ServiceExceptionCode;
import com.sparta.javamarket.domain.user.dto.UserCreateRequest;
import com.sparta.javamarket.domain.user.dto.UserSearchResponse;
import com.sparta.javamarket.domain.user.entity.User;
import com.sparta.javamarket.domain.user.mapper.UserMapper;
import com.sparta.javamarket.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

  @Transactional
  public UserSearchResponse create(UserCreateRequest userCreateRequest) {
    userRepository.save(userMapper.toEntity(userCreateRequest));
    UserSearchResponse userSearchResponse = userRepository.findFirstByEmail(userCreateRequest.getEmail());
    return userSearchResponse;

  }

  @Transactional
  public void update(Long userId, UserCreateRequest userCreateRequest) {
    User user = getUser(userId);

    user.setName(userCreateRequest.getName());
    user.setNickname(userCreateRequest.getNickname());

    userRepository.save(user);
  }

  @Transactional
  public void delete(Long userId) {
    userRepository.deleteById(userId);
  }

  private User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
  }

}
