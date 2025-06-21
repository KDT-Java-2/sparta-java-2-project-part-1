package com.scb.project.commerce.domain.user.repository;

import com.scb.project.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
