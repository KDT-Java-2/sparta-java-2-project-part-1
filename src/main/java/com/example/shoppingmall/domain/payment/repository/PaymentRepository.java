package com.example.shoppingmall.domain.payment.repository;

import com.example.shoppingmall.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
} 