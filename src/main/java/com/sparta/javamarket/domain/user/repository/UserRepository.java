package com.sparta.javamarket.domain.user.repository;

import com.sparta.javamarket.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u JOIN FETCH u.purchases")
  List<User> findAllByWithPurchases();

}
