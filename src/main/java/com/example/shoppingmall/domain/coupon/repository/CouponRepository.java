package com.example.shoppingmall.domain.coupon.repository;

import com.example.shoppingmall.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
} 