package com.sparta.project1.domain.user.repository;

import com.sparta.project1.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository는 지원받는 객체로 스프링부트내 자동빈주입되므로 @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
