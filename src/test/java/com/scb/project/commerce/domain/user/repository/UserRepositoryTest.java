package com.scb.project.commerce.domain.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.common.enums.UserRole;
import com.scb.project.commerce.common.enums.UserStatus;
import com.scb.project.commerce.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("기본 CRUD : 단일 사용자 조회에 성공합니다.")
    void selectUserByIdSuccess() {
        User user = userRepository.findById(3L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        assertThat(user.getId()).isEqualTo(3);
    }

    @Test
    @DisplayName("기본 CRUD : 전체 사용자 조회에 성공합니다.")
    void selectAllUserSuccess() {
        List<User> users = userRepository.findAll();

//        assertThat(users.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("기본 CRUD : 사용자 생성에 성공합니다.")
    void saveUserSuccess() {
        User saveUser = User.builder()
            .name("V테스트 유저V")
            .email("test@gmail.com")
            .phone("010-1234-5678")
            .passwordHash("testtest")
            .role(UserRole.CUSTOMER)
            .status(UserStatus.ACTIVE)
            .build();

        userRepository.save(saveUser);

        assertThat(saveUser.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    @DisplayName("기본 CRUD : 사용자 연락처를 수정합니다.")
    void updateUserPhoneSuccess() {
        User originUser = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        String originPhone = originUser.getPhone();

        originUser.setPhone("010-1111-1111");

        userRepository.save(originUser);

        User updateUser = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        assertThat(updateUser.getPhone()).isNotEqualTo(originPhone);
    }

    @Test
    @DisplayName("기본 CRUD : 사용자 상태를 수정합니다.")
    void updateUserNameSuccess() {
        User originUser = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        originUser.setStatus(UserStatus.INACTIVE);

        userRepository.save(originUser);

        User updateUser = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        assertThat(updateUser.getStatus()).isEqualTo(UserStatus.INACTIVE);
    }

    @Test
    @DisplayName("기본 CRUD : 단일 사용자 삭제에 성공합니다.")
    void deleteUserByIdSuccess() {
        // given
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        // when
        userRepository.delete(user);

        // then
        boolean exist = userRepository.existsById(1L);

        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("기본 CRUD : 사용자 상태가 비활성화인 유저들을 삭제합니다.")
    void deleteInactiveUserSuccess() {
        // given
        List<User> inactiveUsers = userRepository.findAllByStatus(UserStatus.INACTIVE);

        // when
        userRepository.deleteAll(inactiveUsers);

        // then
        List<User> existsInactiveUsers = userRepository.findAllByStatus(UserStatus.INACTIVE);
        assertThat(existsInactiveUsers).isEmpty();
    }
}