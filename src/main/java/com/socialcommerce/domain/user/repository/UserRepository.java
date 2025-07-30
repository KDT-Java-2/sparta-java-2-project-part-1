package com.socialcommerce.domain.user.repository;

import com.socialcommerce.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // find 와 get 의 차이점은 get 은 반드시있고, find 는 있을수도 없을수도 있다로 정의된다.
  Optional<User> findByNameAndEmail(String name, String email);
  boolean existsByEmail(String email);
}
