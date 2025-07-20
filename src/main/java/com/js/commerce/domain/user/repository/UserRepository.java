package com.js.commerce.domain.user.repository;

import com.js.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // 구현체에 등록할 거라 선언체에 안 써도 되지만 명시하는 게 좋음
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String eamil);
  
}
