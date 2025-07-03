package com.sparta.java_02.domain.user.repository;

import com.sparta.java_02.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> FindFirstByNameAndEmail(String name);

}
