package com.sparta.commerce.domain.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sparta.commerce.common.enums.UserStatus;
import com.sparta.commerce.domain.user.entity.User;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void 이메일_형식_검사() {
    User user1 = User.builder()
        .email("invalid email")
        .name("홍길동")
        .passwordHash("1234")
        .status(UserStatus.ACTIVE)
        .build();

    assertThrows(ConstraintViolationException.class, () -> {
      userRepository.save(user1);
    });

    User user2 = User.builder()
        .email("gildong@sparta.com")
        .name("홍길동")
        .passwordHash("1234")
        .status(UserStatus.ACTIVE)
        .build();

    User savedUser = userRepository.save(user2);

    assertEquals("gildong@sparta.com", savedUser.getEmail());
  }
}
