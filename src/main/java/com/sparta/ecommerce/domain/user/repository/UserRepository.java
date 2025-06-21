package com.sparta.ecommerce.domain.user.repository;

import com.sparta.ecommerce.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.name = :name")
  Optional<User> findFirstByName(String name);

  // JpaRepository provides methods for CRUD operations, so no additional methods are needed here.
  // You can define custom query methods if necessary, but for basic operations, this is sufficient.

}
