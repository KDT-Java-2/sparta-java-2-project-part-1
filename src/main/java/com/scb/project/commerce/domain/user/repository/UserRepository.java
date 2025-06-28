package com.scb.project.commerce.domain.user.repository;

import com.scb.project.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * 이메일 중복 여부를 확인합니다.
     *
     * @param email 확인할 이메일 주소
     * @return 해당 이메일이 이미 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByEmail(String email);
}
