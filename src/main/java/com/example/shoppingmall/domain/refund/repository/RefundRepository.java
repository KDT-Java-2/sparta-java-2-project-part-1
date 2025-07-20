package com.example.shoppingmall.domain.refund.repository;

import com.example.shoppingmall.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {
} 