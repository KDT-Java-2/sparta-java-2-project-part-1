package com.sparta.e_project.domain.user.repository;

import com.sparta.e_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is a placeholder for the UserRespository class.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

}
