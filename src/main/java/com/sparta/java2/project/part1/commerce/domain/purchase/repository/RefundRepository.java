package com.sparta.java2.project.part1.commerce.domain.purchase.repository;

import com.sparta.java2.project.part1.commerce.domain.purchase.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {

}
