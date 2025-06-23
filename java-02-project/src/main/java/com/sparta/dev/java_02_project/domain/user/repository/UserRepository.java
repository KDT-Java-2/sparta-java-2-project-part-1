package com.sparta.dev.java_02_project.domain.user.repository;

import com.sparta.dev.java_02_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
