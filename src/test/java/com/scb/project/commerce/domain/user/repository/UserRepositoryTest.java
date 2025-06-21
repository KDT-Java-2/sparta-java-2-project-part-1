package com.scb.project.commerce.domain.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.scb.project.commerce.common.enums.UserRole;
import com.scb.project.commerce.domain.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("기본 CRUD : 사용자 생성에 성공합니다.")
    void saveUserSuccess() {
        User saveUser = User.builder()
            .name("V테스트 유저V")
            .email("test@gmail.com")
            .phone("010-1234-5678")
            .passwordHash("testtest")
            .build();

        userRepository.save(saveUser);

        Assertions.assertThat(saveUser.getId()).isEqualTo(5);
    }

}