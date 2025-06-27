package com.sparta.dev.java_02_project.domain.user.repository;

import com.sparta.dev.java_02_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.name = :name")
    Optional<User> findByName(String name);
}
