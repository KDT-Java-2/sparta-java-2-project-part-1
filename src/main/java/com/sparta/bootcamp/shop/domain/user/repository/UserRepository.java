package com.sparta.bootcamp.shop.domain.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.sparta.bootcamp.shop.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  List<User> findByCreatedAtAfterOrderByNameAsc(LocalDateTime dateTime);

  long countByName(String name);

  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findUserByEmail(@Param("email") String email);

  @Query("SELECT u FROM User u JOIN FETCH u.purchases")
  List<User> findAllWithPurchases();

}