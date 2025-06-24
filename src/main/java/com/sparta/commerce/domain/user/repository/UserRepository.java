package com.sparta.commerce.domain.user.repository;

import com.sparta.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
