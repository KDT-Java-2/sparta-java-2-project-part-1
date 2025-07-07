package com.example.shoppingmall.domain.user.entity;

import com.example.shoppingmall.common.enums.Gender;
import com.example.shoppingmall.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 10명 생성 및 customerNumber/uuid 자동 생성 테스트")
    void createTenUsers() {
        for (int i = 1; i <= 10; i++) {
            User user = User.builder()
                    .name("테스트유저" + i)
                    .email("test" + i + "@example.com")
                    .passwordHash("encodedPassword" + i)
                    .phone("010-0000-000" + i)
                    .gender(Gender.MALE)
                    .birthDate(LocalDate.of(1990, 1, i))
                    .profileImageUrl(null)
                    .termsAgreedAt(LocalDateTime.now())
                    .privacyAgreedAt(LocalDateTime.now())
                    .marketingAgreedAt(null)
                    .build();
            userRepository.save(user);
        }
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(10);
        users.forEach(u -> {
            System.out.println("customerNumber: " + u.getCustomerNumber() + ", uuid: " + u.getUuid() + ", email: " + u.getEmail() + ", name: " + u.getName());
            assertThat(u.getCustomerNumber()).startsWith("C");
            assertThat(u.getUuid()).isNotNull();
        });
    }
} 