package com.example.shoppingmall.domain.user.repository;

import com.example.shoppingmall.domain.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
} 