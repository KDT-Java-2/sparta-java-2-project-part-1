package com.scb.project.commerce.domain.refund.repository;

import com.scb.project.commerce.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

}
