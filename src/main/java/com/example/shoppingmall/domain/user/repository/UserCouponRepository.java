package com.example.shoppingmall.domain.user.repository;

import com.example.shoppingmall.domain.user.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
} 