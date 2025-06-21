package com.scb.project.commerce.domain.user.repository;

import com.scb.project.commerce.common.enums.UserStatus;
import com.scb.project.commerce.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 사용자 상태 값으로 사용자 객체 찾는 메서드
     *
     * @param status 사용자 상태 (enum)
     * @return 사용자 객체 리스트
     */
    List<User> findAllByStatus(UserStatus status);
}
