package com.example.shoppingmall.domain.refund.repository;

import com.example.shoppingmall.domain.refund.entity.RefundItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundItemRepository extends JpaRepository<RefundItem, Long> {
} 