package com.sparta.java2.project.part1.commerce.domain.refund.repository;

import com.sparta.java2.project.part1.commerce.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {

}
