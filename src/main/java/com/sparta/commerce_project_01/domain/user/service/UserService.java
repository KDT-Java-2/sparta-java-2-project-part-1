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
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final EntityManager entityManager;
  private final JdbcTemplate jdbcTemplate;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;  // BCryptPasswordEncoder 주입

  private final UserRepository userRepository;
  private final UserQueryRepository userQueryRepository;

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

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

  @Transactional(readOnly = true)
  public void delete(Long id) {
    User user = getUser(id);
    userRepository.delete(user);
  }

  @Transactional
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


  @Transactional
  public UserResponse create(@Valid UserCreateRequest request) {

    Optional<User> savedUser;
    userRepository.findByEmail(request.getEmail())
        .ifPresent(user -> {
          throw new ServiceException(ServiceExceptionCode.USER_ALREADY_EXIST);
        });

    String hashedPassword = passwordEncoder.encode(request.getPassword());

    User user = userRepository.save(User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .passwordHash(hashedPassword)
        .cellPhone(request.getCellPhone())
        .acceptTerms(request.isAcceptTerms())
        .acceptPrivacy(request.isAcceptPrivacy())
        .acceptMarketing(request.isAcceptMarketing())
        .build());

    log.info("User created: {}", user);
    return userMapper.toResponse(user);
  }

  @Transactional
  public Page<UserSearchResponse> searchAllUser() {
    return null;
  }

  @Transactional
  public void saveAllUsers(List<User> users) {
    String sql = "INSERT INTO user (name, email, password_hash) VALUES (?, ?, ?)";

    jdbcTemplate.batchUpdate(sql, users, 1000, (ps, user) -> {
      LocalDateTime now = LocalDateTime.now();
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPasswordHash());
    });
  }

  @Transactional
  public void saveAllUsersWithEntityManager(List<User> users) {
    int batchSize = 1000;
    for (int i = 0; i < users.size(); i++) {
      User user = users.get(i);
      entityManager.persist(user);

      // 1000건마다 DB에 반영하고 메모리를 비운다.
      if ((i + 1) % batchSize == 0) {
        // 1. DB에 쿼리 전송 (데이터 저장) : AUTO INCREMENT 필드 문제 해결해야함
        // -> DB가 SEQUENCE를 지원하고 SEQUENCE로 설정되어야함 (mysql은  SEQUENCE 지원안함)
        entityManager.flush();
        // 2. 영속성 컨텍스트 초기화 (메모리 확보)
        entityManager.clear();
      }
    }
    // 루프 종료 후 남은 데이터 처리
    entityManager.flush();
    entityManager.clear();
  }


}