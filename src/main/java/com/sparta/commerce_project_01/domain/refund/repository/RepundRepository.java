package com.sparta.commerce_project_01.domain.refund.repository;

import com.sparta.commerce_project_01.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepundRepository extends JpaRepository<Refund, Long> {
}
