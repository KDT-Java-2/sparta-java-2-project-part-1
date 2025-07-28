package com.sparta.coupang_commerce.domain.user.repository;

import com.sparta.coupang_commerce.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findFirstByNameAndEmail(String name, String email);

  @Query("select u from User u where u.email = :name")
  Optional<User> findUser(String name);
}
