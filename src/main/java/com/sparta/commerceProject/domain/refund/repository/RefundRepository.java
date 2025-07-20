package com.sparta.commerceProject.domain.refund.repository;

import com.sparta.commerceProject.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

}
