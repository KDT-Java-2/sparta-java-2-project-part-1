package com.sparta.ecommerce.domain.user.repository;

import com.sparta.ecommerce.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByEmail(String email);
}
