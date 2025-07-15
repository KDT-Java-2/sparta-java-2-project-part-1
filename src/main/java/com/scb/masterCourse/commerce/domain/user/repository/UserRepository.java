package com.scb.masterCourse.commerce.domain.user.repository;

import com.scb.masterCourse.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);
}
